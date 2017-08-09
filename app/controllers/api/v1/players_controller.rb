class Api::V1::PlayersController < Api::V1::BaseController
  def index
    render :json => Player.all
  end
end
