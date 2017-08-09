require 'rails_helper'

RSpec.describe Player, type: :model do
  it { should validate_presence_of(:battle_tag) }
  it { should validate_presence_of(:player_icon) }
  it { should validate_presence_of(:platform) }
  it { should validate_presence_of(:region) }
end