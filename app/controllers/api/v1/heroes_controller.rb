class Api::V1::HeroesController < Api::V1::BaseController
  def index
    render :json => Hero.all
  end
end
