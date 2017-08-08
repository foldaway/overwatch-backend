package me.duncanleo.overwatchdashboard.json

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import me.duncanleo.overwatchdashboard.model.Player
import java.lang.reflect.Type

class PlayerSerializer : JsonSerializer<Player> {
    override fun serialize(src: Player?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val obj = JsonObject()
        obj.addProperty("battle_tag", src?.battleTag)
        obj.addProperty("player_icon", src?.playerIcon)
        obj.addProperty("platform", src?.platform)
        obj.addProperty("region", src?.region)
        return obj
    }
}
