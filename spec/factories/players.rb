FactoryBot.define do
  factory :player do
    battle_tag { Faker::Lorem.word }
    player_icon { "https://example.com/player_icon.jpg" }
    platform { ["pc", "psn", "xbl"].sample }
    region { ["us", "eu", "kr", "cn", "global"].sample }
  end
end
