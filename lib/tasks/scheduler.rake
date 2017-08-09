desc "This task fetches data from the unofficial Overwatch API."

class NodeOWAPI
  include HTTParty
  base_uri  'ow-api.herokuapp.com'

  def initialize(player)
    @player = player
  end

  def profile
    self.class.get("/profile/#{@player.platform}/#{@player.region}/#{@player.battle_tag}")
  end

  def stats
    self.class.get("/stats/#{@player.platform}/#{@player.region}/#{@player.battle_tag}")
  end
end

task :fetch_data => :environment do
  Player.all.each do |player|
    puts "Processing '#{player.battle_tag}'"

    ow_api = NodeOWAPI.new(player)
    puts ow_api.profile
  end
end