require 'rails_helper'

RSpec.describe PlayerData, type: :model do
  it { should validate_presence_of(:level) }
  it { should validate_presence_of(:sr) }
  it { should validate_presence_of(:player) }
  it { should validate_presence_of(:mainQP) }
end
