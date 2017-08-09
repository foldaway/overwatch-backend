class Api::V1::PlayerDatasController < Api::V1::BaseController
  def index
    render :json => PlayerData.all
  end
end
