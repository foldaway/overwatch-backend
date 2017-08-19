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

def to_integer(str)
  str.to_i if Integer(str) rescue -1
end

desc "Fetch players' data from the OW API"
task :fetch_data => :environment do
  Player.all.each do |player|
    puts "Processing '#{player.battle_tag}'"

    ow_api = NodeOWAPI.new(player)
    profile = ow_api.profile
    stats = ow_api.stats['stats']
    
    player.player_icon = profile['portrait']
    player.save!

    puts "Updated player icon"

    main_qp_hero = Hero
      .where(name: stats['top_heroes']['quickplay'].first['hero'])
      .first_or_create(
        name: stats['top_heroes']['quickplay'].first['hero'],
        img: stats['top_heroes']['quickplay'].first['img']
      )

    main_comp_hero = stats['top_heroes']['competitive'].any? ?
    Hero.where(name: stats['top_heroes']['competitive'].first['hero'],)
      .first_or_create(
      name: stats['top_heroes']['competitive'].first['hero'],
      img: stats['top_heroes']['competitive'].first['img']
    ) : nil

    puts "Updated main-ed heroes"

    player_data = PlayerData.create(
      level: profile['level'].to_i,
      sr: to_integer(profile['competitive']['rank']),
      player: player,
      mainQP: main_qp_hero,
      mainComp: main_comp_hero
    )

    puts "Added new player data"
  end
end
