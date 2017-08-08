class CreatePlayers < ActiveRecord::Migration[5.1]
  def change
    create_table :players do |t|
      t.string :battle_tag
      t.string :player_icon
      t.string :platform
      t.string :region

      t.timestamps
    end
  end
end
