class Api::V1::PlayerDatasController < ApplicationController
  def index
    render :json => PlayerData.all
  end

  def show_by_player_id
    render :json => PlayerData.joins(:player).where(players: { id: params[:id]}).order('created_at DESC')
  end
end
