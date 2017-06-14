package me.duncanleo.overwatchdashboard.network.model

import com.squareup.moshi.Json

/**
 * Root response object from OWAPI Stats
 */
data class UserStatsResponse(
        @Json(name = "kr") val korea: Region?,
        @Json(name = "eu") val europe: Region?,
        @Json(name = "us") val usa: Region?,
        val any: Any? // Not sure what this key is supposed to be
) {
    data class Region(
            val stats: Stats
    ) {
        data class Stats(
                val competitive: UserStat?,
                val quickplay: UserStat?
        )
    }
}