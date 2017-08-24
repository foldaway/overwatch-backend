class Api::V1::PlayersController < Api::V1::BaseController
  def index
    render :json => Player.all.order('LOWER(battle_tag) ASC')
  end

  def show
    render :json => Player.find(params[:id])
  end
end
