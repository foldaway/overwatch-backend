require 'rails_helper'

RSpec.describe 'Player Data API', type: :request do
  let!(:player_datas) { create_list(:player_data, 10) }

  describe 'GET /api/v1/player_datas' do
    before { get '/api/v1/player_datas' }

    it 'returns heroes' do
      expect(json).not_to be_empty
      expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end
end
