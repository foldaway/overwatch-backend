class PlayerData < ApplicationRecord
  belongs_to :player
  belongs_to :mainQP, :class_name => "Hero"
  belongs_to :mainComp, :class_name => "Hero", :optional => true
  validates_presence_of :level, :sr, :player, :mainQP
end
