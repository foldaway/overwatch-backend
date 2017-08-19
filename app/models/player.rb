class Player < ApplicationRecord
  validates_presence_of :battle_tag, :player_icon, :platform, :region
  has_many :datas, :class_name => "PlayerData", dependent: :destroy
end
