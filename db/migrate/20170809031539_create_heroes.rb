class CreateHeroes < ActiveRecord::Migration[5.1]
  def change
    create_table :heroes do |t|
      t.string :name, null: false
      t.string :img, null: false

      t.timestamps
    end
  end
end
