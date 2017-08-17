require 'rails_helper'

RSpec.describe 'Heroes API', type: :request do
  let!(:heroes) { create_list(:hero, 10) }

  describe 'GET /api/v1/heroes' do
    before { get '/api/v1/heroes' }

    it 'returns heroes' do
      expect(json).not_to be_empty
      expect(json.size).to eq(10)
    end

    it 'returns status code 200' do
      expect(response).to have_http_status(200)
    end
  end
end
