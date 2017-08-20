class Api::V1::HeroesController < Api::V1::BaseController
  def index
    render :json => Hero.all
  end

  def show
    render :json => Hero.find(params[:id])
  end
end
