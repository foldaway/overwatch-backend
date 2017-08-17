class Player < ApplicationRecord
  validates_presence_of :battle_tag, :player_icon, :platform, :region
end
