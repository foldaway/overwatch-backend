# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

Player.create!(
  battle_tag: "Wraxu-1747",
  player_icon: "https://example.com/icon.jpg",
  platform: "pc",
  region: "us"
)

Player.create!(
  battle_tag: "Seagull-1894",
  player_icon: "https://example.com/icon.jpg",
  platform: "pc",
  region: "us"
)
