desc "This task fetches data from the unofficial Overwatch API."

require 'i18n'

HEROES = [
  'Genji',
  'Bastion',
  'Doomfist',
  'Hanzo',
  'Junkrat',
  'McCree',
  'Mei',
  'Pharah',
  'Reaper',
  'Soldier: 76',
  'Sombra',
  'Symmetra',
  'Torbjorn',
  'Tracer',
  'Widowmaker',
  'D.Va',
  'Orisa',
  'Reinhardt',
  'Roadhog',
  'Winston',
  'Wrecking Ball',
  'Zarya',
  'Ana',
  'Brigitte',
  'Lucio',
  'Mercy',
  'Moira',
  'Zenyatta'
]

class NodeOWAPI
  include HTTParty
  base_uri  'ow-api.com/v1'

  def initialize(player)
    @player = player
  end

  def stats
    self.class.get("/stats/#{@player.platform}/#{@player.region}/#{@player.battle_tag}/profile")
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
    stats = ow_api.stats

    player.player_icon = stats['icon']
    player.save!

    puts "Updated player icon"

    main_qp_hero_name = HEROES.sample

    main_qp_hero = Hero
      .where(name: main_qp_hero_name)
      .first_or_create(
        name: main_qp_hero_name,
        img: get_hero_image(main_qp_hero_name)
      )
    main_qp_hero.update(img: get_hero_image(main_qp_hero_name))

    main_comp_hero_name = !stats['rating'].empty? ? HEROES.sample : ''

    main_comp_hero = !stats['rating'].empty? ?
    Hero.where(name: main_comp_hero_name,)
      .first_or_create(
      name: main_comp_hero_name,
      img: get_hero_image(main_comp_hero_name)
    ) : nil
    main_comp_hero&.update(img: get_hero_image(main_comp_hero_name))

    puts "Updated main-ed heroes"

    PlayerData.create(
      level: stats['prestige'].to_i * 100 + stats['level'].to_i,
      sr: to_integer(stats['rating']),
      player: player,
      mainQP: main_qp_hero,
      mainComp: main_comp_hero
    )

    puts "Added new player data"
  end
end
