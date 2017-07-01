package me.duncanleo.overwatchdashboard.model

import me.duncanleo.overwatchdashboard.network.model.PlayerProfileResponse
import me.duncanleo.overwatchdashboard.network.model.PlayerStatsResponse

/**
 * Model class representing an Overwatch player
 */
data class Player(
        val battleTag: String,
        val platform: String,
        val region: String,
        val stats: PlayerStatsResponse,
        val profile: PlayerProfileResponse
) {
    val battleTagName: String
        get() = profile.username

    val battleTagNumber: String
        get() = battleTag.replace("$battleTag-", "")

    val seasonRating: Int?
        get() = profile.competitive?.rank

    val avatar: String?
        get() = profile.portrait

    val mainQP: String?
        get() = fixString(stats.stats?.topHeroes?.quickplay?.first()?.name)

    val mainComp: String?
        get() = fixString(stats.stats?.topHeroes?.competitive?.first()?.name)

    fun fixString(str: String?): String? {
        return str?.toLowerCase()
                ?.replace(".", "")
                ?.replace("ú", "u")
                ?.replace(": ", "-")
                ?.replace("ö", "o")
    }
}