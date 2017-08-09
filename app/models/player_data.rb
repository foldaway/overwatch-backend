class PlayerData < ApplicationRecord
  belongs_to :player
  has_one :mainQP, :class_name => "Hero"
  has_one :mainComp, :class_name => "Hero"
  validates_presence_of :level, :sr, :player, :mainQP
end
