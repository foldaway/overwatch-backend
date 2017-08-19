namespace :player do
  desc "Add a player"
  task :add, [:battle_tag, :platform, :region] => :environment do |t, args|
    platform = args[:platform] || "pc"
    region = args[:region] || "us"

    puts "Adding player '#{args[:battle_tag]}' on '#{platform}' in the '#{region}'"
    Player.create!(
      battle_tag: args[:battle_tag],
      player_icon: "https://example.com/icon.jpg",
      platform: platform,
      region: region
    )
  end

  desc "Remove a player"
  task :remove, [:battle_tag, :platform, :region] => :environment do |t, args|
    platform = args[:platform] || "pc"
    region = args[:region] || "us"

    puts "Removing player '#{args[:battle_tag]}' on '#{platform}' in the '#{region}'"
    Player.where(
      battle_tag: args[:battle_tag],
      platform: platform,
      region: region
    ).destroy_all
  end
end