class AddEndorsementLevelToPlayerData < ActiveRecord::Migration[5.2]
  def change
    add_column :player_data, :endorsement_level, :integer
  end
end
