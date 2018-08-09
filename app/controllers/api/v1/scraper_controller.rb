require 'playoverwatch-scraper'

class Api::V1::ScraperController < ApplicationController
  def index
    battle_tag = params[:battle_tag]

    scraper = PlayOverwatch::Scraper.new(battle_tag)

    render :json => {
      :player_icon => scraper.player_icon,
      :player_level => scraper.player_level,
      :endorsement_level => scraper.endorsement_level,
      :sr => scraper.sr,
      :main_qp => scraper.main_qp,
      :main_comp => scraper.main_comp
    }
  end
end
