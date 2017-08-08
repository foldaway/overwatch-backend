package me.duncanleo.overwatchdashboard.json

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.model.PlayerData
import java.lang.reflect.Type

class PlayerDataSerializer : JsonSerializer<PlayerData> {
    override fun serialize(src: PlayerData?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val obj = JsonObject()
        obj.addProperty("level", src?.level)
        obj.addProperty("sr", src?.sr)
        obj.add("date", context?.serialize(src?.date))
        obj.add("mainQP", context?.serialize(src?.mainQP))
        obj.add("mainComp", context?.serialize(src?.mainComp))
        return obj
    }
}
