package me.duncanleo.overwatchdashboard.json

import com.google.gson.GsonBuilder
import me.duncanleo.overwatchdashboard.model.Player
import spark.ResponseTransformer

class JsonTransformer : ResponseTransformer {
    val gson = GsonBuilder()
            .registerTypeAdapter(Player::class.java, PlayerSerializer())
            .create()

    override fun render(model: Any?): String {
        return gson.toJson(model)
    }
}
