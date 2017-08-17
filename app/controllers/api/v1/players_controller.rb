class Api::V1::PlayersController < Api::V1::BaseController
  def index
    render :json => Player.all
  end

  def show
    render :json => Player.find(params[:id])
  end
end
