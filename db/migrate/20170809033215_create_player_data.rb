class CreatePlayerData < ActiveRecord::Migration[5.1]
  def change
    create_table :player_data do |t|
      t.integer :level, null: false
      t.integer :sr, null: false
      t.references :player, foreign_key: true
      t.references :mainQP, foreign_key: { to_table: :heroes }
      t.references :mainComp, foreign_key: { to_table: :heroes }, null: true

      t.timestamps
    end
  end
end
