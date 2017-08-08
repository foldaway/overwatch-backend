package me.duncanleo.overwatchdashboard.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by duncanleo on 29/6/17.
 */
data class PlayerProfileResponse (
        val username: String,
        val level: Int,
        val portrait: String,
        val games: GameModes,
        val playtime: PlayTimes,
        val competitive: CompData?,
        val levelFrame: String,
        val star: String
) {
    data class CompData (
            val rank: Int?,
            @SerializedName("rank_img") val rankImg: String?
    )
    data class PlayTimes (
            val quickplay: String?,
            val competitive: String?
    )
    data class GameMode (
            val won: Int?,
            val lost: Int?,
            val draw: Int?,
            val played: Int?
    )
    data class GameModes (
            val quickplay: GameMode?,
            val competitive: GameMode?
    )
}