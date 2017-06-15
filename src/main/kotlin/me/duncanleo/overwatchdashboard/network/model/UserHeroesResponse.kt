package me.duncanleo.overwatchdashboard.network.model

import com.squareup.moshi.Json

/**
 * Created by duncanleo on 15/6/17.
 */
data class UserHeroesResponse (
        @Json(name = "kr") val korea: Region?,
        @Json(name = "eu") val europe: Region?,
        @Json(name = "us") val usa: Region?,
        val any: Any?
) {
    data class Region(
            val playtime: Playtime,
            val stats: Stats
    ) {
        data class Playtime(
                val competitive: Map<String, Double>?,
                val quickplay: Map<String, Double>?
        )
        data class Stats(
                val competitive: Map<String, Hero>?,
                val quickplay: Map<String, Hero>?
        ) {
            data class Hero(
                    @Json(name = "average_stats") val averageStats: Map<String, Double>,
                    @Json(name = "hero_stats") val heroStats: Map<String, Double>,
                    @Json(name = "general_stats") val generalStats: Map<String, Double>
            )
        }
    }
}