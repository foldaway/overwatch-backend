Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root 'home#index'
  namespace :api do
    namespace :v1 do
      resources :players, only: [:index, :show]
      get 'players/:id/data', to: 'player_datas#show_by_player_id'
      resources :heroes, only: [:index, :show]
      resources :player_datas, only: [:index]
    end
  end
  match "*path", to: "home#index", via: :all
end
