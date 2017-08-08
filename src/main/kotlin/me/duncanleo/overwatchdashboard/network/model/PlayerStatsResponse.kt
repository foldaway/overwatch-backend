package me.duncanleo.overwatchdashboard.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by duncanleo on 29/6/17.
 */
data class PlayerStatsResponse (
        val username: String,
        val level: Int,
        val portrait: String,
        val stats: Stats?
) {
    data class Stats (
            @SerializedName("top_heroes") val topHeroes: GameModesContainerTH,
            val combat: GameModesContainer,
            val deaths: GameModesContainer,
            @SerializedName("match_awards") val matchAwards: GameModesContainer,
            val assists: GameModesContainer,
            val average: GameModesContainer,
            @SerializedName("miscellaneous") val misc: GameModesContainer,
            val best: GameModesContainer,
            val game: GameModesContainer
    ) {
        data class GameModesContainerTH (
            val quickplay: List<Hero>,
            val competitive: List<Hero>
        )

        data class GameModesContainer (
                val quickplay: List<Stat>?,
                val competitive: List<Stat>?
        )

        data class Hero (
                @SerializedName("hero") val name: String,
                val played: String,
                val img: String
        )

        data class Stat (
                val title: String,
                val value: String
        )
    }
}