FactoryBot.define do
  factory :hero do
    name { Faker::Lorem.word }
    img { "https://example.com/hero.jpg" }
  end
end
