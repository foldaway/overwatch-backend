FactoryBot.define do
  factory :player_data do
    association :player, factory: :player
    association :mainQP, factory: :hero
    level { Random.new.rand(1..1000) }
    sr { Random.new.rand(0..5000) }
  end
end
