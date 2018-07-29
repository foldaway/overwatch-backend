require 'json'

class OWScraper
  def initialize(battle_tag)
    @player_page = Nokogiri::HTML(open("https://playoverwatch.com/en-us/career/pc/#{battle_tag}"))
  end

  def player_icon
    @player_page.css('img.player-portrait').first["src"]
  end

  def player_level
    quotient = 0
    remainder = @player_page.css('.player-level .u-vertical-center').first.content.to_i
    level_stars_image_url_code = @player_page.css('.player-rank').first['style'].scan(/0x0250000000000(.+?)_Rank.png/i).flatten
    if level_stars_image_url_code.any?
      quotient = 100 * rank_map[level_stars_image_url_code.first]
    end
    return quotient + remainder
  end

  def endorsement_level
    @player_page.css('.endorsement-level .u-center').first.content.to_i
  end

  def sr
    content = @player_page.css('.competitive-rank > .h5').first.content
    content.to_i if Integer(content) rescue -1
  end

  private
  def rank_map
    JSON.parse File.read(File.expand_path('./ranks.json', __dir__))
  end
end
