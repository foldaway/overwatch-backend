package me.duncanleo.overwatchdashboard.model

import me.duncanleo.overwatchdashboard.network.model.UserHeroesResponse
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse

/**
 * Model class representing an Overwatch player
 */
data class Player(
        val battleTag: String,
        val stats: UserStatsResponse,
        val heroes: UserHeroesResponse
) {
    val seasonRating: Double?
    get() {
        return (
                stats.usa?.stats?.competitive?.overallStats?.get("comprank") as Double? ?:
                stats.korea?.stats?.competitive?.overallStats?.get("comprank") as Double? ?:
                stats.europe?.stats?.competitive?.overallStats?.get("comprank") as Double?
                )
    }

    val avatar: String?
    get() {
        return (
                stats.korea?.stats?.competitive?.overallStats?.get("avatar") as String? ?:
                stats.europe?.stats?.competitive?.overallStats?.get("avatar") as String? ?:
                stats.usa?.stats?.competitive?.overallStats?.get("avatar") as String?
                )
    }
}