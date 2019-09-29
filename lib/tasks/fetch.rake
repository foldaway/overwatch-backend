desc "This task fetches data from the unofficial Overwatch API."

require 'i18n'
require 'playoverwatch-scraper'

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
  'Sigma',
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
  'Zenyatta',
  'Ashe'
]

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

    begin  
      player_scraper = PlayOverwatch::Scraper.new(player.battle_tag)

      player.player_icon = player_scraper.player_icon
      player.player_icon = '_' if player.player_icon.nil?
      player.save!

      main_qp_hero_name = HEROES.sample

      main_qp_hero = Hero
        .where('lower(name) = ?', main_qp_hero_name.downcase)
        .first_or_create(
          name: main_qp_hero_name,
          img: get_hero_image(main_qp_hero_name)
        )
      main_qp_hero.update(img: get_hero_image(main_qp_hero_name))

      if player_scraper.sr != -1
        main_comp_hero_name = HEROES.sample

        main_comp_hero = Hero
          .where('lower(name) = ?', main_comp_hero_name.downcase)
          .first_or_create(
            name: main_comp_hero_name,
            img: get_hero_image(main_comp_hero_name)
          )
        main_comp_hero&.update(img: get_hero_image(main_comp_hero_name))
      end

      PlayerData.create(
        level: player_scraper.player_level,
        sr: player_scraper.sr,
        endorsement_level: player_scraper.endorsement_level,
        player: player,
        mainQP: main_qp_hero,
        mainComp: main_comp_hero
      )

      puts "Processing complete"
      sleep 3
    rescue => exception
      puts exception
      puts exception.backtrace
    end
  end
end
