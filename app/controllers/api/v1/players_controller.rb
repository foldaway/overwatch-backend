class Api::V1::PlayersController < Api::V1::BaseController
  def index
    respond_with Player.all
  end
end