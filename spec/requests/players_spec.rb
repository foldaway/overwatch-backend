require 'rails_helper'

RSpec.describe 'Players API', type: :request do
  let!(:players) { create_list(:player, 10) }
  let(:player_id) { players.first.id }

  describe 'GET /api/v1/players' do
    before { get '/api/v1/players' }

    it 'returns players' do
      expect(json).not_to be_empty
      expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end
end
