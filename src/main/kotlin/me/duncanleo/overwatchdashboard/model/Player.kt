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
    val battleTagName: String
    get() = battleTag.split('-')[0]

    val battleTagNumber: String
        get() = battleTag.split('-')[1]

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

    val mainQP: String?
    get() {
        return (
                heroes.korea?.heroes?.playtime?.quickplay?.asIterable()?.sortedByDescending { it.value }?.first()?.key ?:
                heroes.europe?.heroes?.playtime?.quickplay?.asIterable()?.sortedByDescending { it.value }?.first()?.key ?:
                heroes.usa?.heroes?.playtime?.quickplay?.asIterable()?.sortedByDescending { it.value }?.first()?.key
                )
    }

    val mainComp: String?
    get() {
        return (
                heroes.korea?.heroes?.playtime?.competitive?.asIterable()?.sortedByDescending { it.value }?.first()?.key ?:
                heroes.europe?.heroes?.playtime?.competitive?.asIterable()?.sortedByDescending { it.value }?.first()?.key ?:
                heroes.usa?.heroes?.playtime?.competitive?.asIterable()?.sortedByDescending { it.value }?.first()?.key
                )
    }
}