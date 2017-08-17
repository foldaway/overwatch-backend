class Api::V1::PlayerDatasController < Api::V1::BaseController
  def index
    render :json => PlayerData.all
  end

  def show_by_player_id
    render :json => PlayerData.joins(:player).where(players: { id: params[:id]})
  end
end
