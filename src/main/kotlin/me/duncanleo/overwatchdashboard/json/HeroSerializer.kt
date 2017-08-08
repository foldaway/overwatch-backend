package me.duncanleo.overwatchdashboard.json

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import me.duncanleo.overwatchdashboard.model.Hero
import me.duncanleo.overwatchdashboard.model.Player
import java.lang.reflect.Type

class HeroSerializer : JsonSerializer<Hero> {
    override fun serialize(src: Hero?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val obj = JsonObject()
        obj.addProperty("name", src?.name)
        obj.addProperty("img", src?.img)
        return obj
    }
}
