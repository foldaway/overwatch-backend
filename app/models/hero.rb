class Hero < ApplicationRecord
  self.table_name = "heroes"
  validates_presence_of :name, :img
end
