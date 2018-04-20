class Api::V1::PlayersController < ApplicationController
  def index
    render :json => Player.all.order('LOWER(battle_tag) ASC')
  end

  def show
    render :json => Player.find(params[:id])
  end
end
