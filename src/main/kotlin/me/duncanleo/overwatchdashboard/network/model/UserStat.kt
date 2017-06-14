package me.duncanleo.overwatchdashboard.network.model

import com.squareup.moshi.Json

/**
 * Created by duncanleo on 14/6/17.
 */
data class UserStat(
        @Json(name = "overall_stats") val overallStats: Map<String, Any>,
        @Json(name = "game_stats") val gameStats: Map<String, Any>,
        val competitive: Boolean,
        @Json(name = "average_stats") val averageStats: Map<String, Any>
)