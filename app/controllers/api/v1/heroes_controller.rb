class Api::V1::HeroesController < ApplicationController
  def index
    render :json => Hero.all
  end

  def show
    render :json => Hero.find(params[:id])
  end
end
