class Hero < ApplicationRecord
  validates_presence_of :name, :img
end
