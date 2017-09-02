desc "This task fetches data from the unofficial Overwatch API."

require 'i18n'

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

def normalise_hero_name(name)
  name = I18n.transliterate(name.downcase)
  name.gsub!(/\s/, '-')
  name.gsub!(/[^A-Za-z0-9\-]/, '')
  name
end

def get_hero_image(name)
  "https://blzgdapipro-a.akamaihd.net/hero/#{normalise_hero_name(name)}/hero-select-portrait.png"
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

    main_qp_hero_name = stats['top_heroes']['quickplay'].first['hero']

    main_qp_hero = Hero
      .where(name: main_qp_hero_name)
      .first_or_create(
        name: main_qp_hero_name,
        img: get_hero_image(main_qp_hero_name)
      )
    main_qp_hero.update(img: get_hero_image(main_qp_hero_name))

    main_comp_hero_name = stats['top_heroes']['competitive'].any? ? stats['top_heroes']['competitive'].first['hero'] : ''

    main_comp_hero = stats['top_heroes']['competitive'].any? ?
    Hero.where(name: main_comp_hero_name,)
      .first_or_create(
      name: main_comp_hero_name,
      img: get_hero_image(main_comp_hero_name)
    ) : nil
    main_comp_hero&.update(img: get_hero_image(main_comp_hero_name))

    puts "Updated main-ed heroes"

    PlayerData.create(
      level: profile['level'].to_i,
      sr: to_integer(profile['competitive']['rank']),
      player: player,
      mainQP: main_qp_hero,
      mainComp: main_comp_hero
    )

    puts "Added new player data"
  end
end
