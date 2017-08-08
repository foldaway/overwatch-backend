package me.duncanleo.overwatchdashboard.json

import com.google.gson.GsonBuilder
import me.duncanleo.overwatchdashboard.model.Hero
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.model.PlayerData
import spark.ResponseTransformer

class JsonTransformer : ResponseTransformer {
    val gson = GsonBuilder()
            .registerTypeAdapter(Player::class.java, PlayerSerializer())
            .registerTypeAdapter(PlayerData::class.java, PlayerDataSerializer())
            .registerTypeAdapter(Hero::class.java, HeroSerializer())
            .create()

    override fun render(model: Any?): String {
        return gson.toJson(model)
    }
}
