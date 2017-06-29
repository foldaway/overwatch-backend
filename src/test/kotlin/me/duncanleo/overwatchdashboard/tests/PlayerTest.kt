package me.duncanleo.overwatchdashboard.tests

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.network.model.PlayerProfileResponse
import me.duncanleo.overwatchdashboard.network.model.PlayerStatsResponse
import org.junit.runner.RunWith

/**
 * Created by duncanleo on 19/6/17.
 */

@RunWith(KTestJUnitRunner::class)
class PlayerTest : ShouldSpec() {
    init {
        val genericGMC = PlayerStatsResponse.Stats.GameModesContainer(
                quickplay = listOf(),
                competitive = listOf()
        )

        val player = Player(
                "someone-12345",
                platform = "pc",
                region = "us",
                stats = PlayerStatsResponse(
                        username = "someone",
                        portrait = "https://example.com/avatar.jpg",
                        level = 99,
                        stats = PlayerStatsResponse.Stats(
                                topHeroes = PlayerStatsResponse.Stats.GameModesContainerTH(
                                        quickplay = listOf(PlayerStatsResponse.Stats.Hero("junkrat", "22 hours", "https://example.com/junkrat.png")),
                                        competitive = listOf(PlayerStatsResponse.Stats.Hero("ana", "22 hours", "https://example.com/ana.png"))
                                ),
                                matchAwards = genericGMC,
                                combat = genericGMC,
                                deaths = genericGMC,
                                assists = genericGMC,
                                average = genericGMC,
                                misc = genericGMC,
                                best = genericGMC,
                                game = genericGMC
                        )
                ),
                profile = PlayerProfileResponse(
                        username = "someone",
                        level = 99,
                        portrait = "https://example.com/avatar.jpg",
                        playtime = PlayerProfileResponse.PlayTimes(
                                quickplay = "100 hours",
                                competitive = "100 hours"
                        ),
                        games = PlayerProfileResponse.GameModes(
                                quickplay = null,
                                competitive = PlayerProfileResponse.GameMode(
                                        won = 10,
                                        lost = 0,
                                        draw = 0,
                                        played = 10
                                )
                        ),
                        competitive = PlayerProfileResponse.CompData(
                                rank = 1234,
                                rankImg = ""
                        ),
                        levelFrame = "",
                        star = ""
                )
        )

        should("player season rating should return competitive SR") {
            player.seasonRating shouldBe 1234
        }

        should("player avatar should be reflected") {
            player.avatar shouldBe "https://example.com/avatar.jpg"
        }

        should("player main in QP should be reflected") {
            player.mainQP shouldBe "junkrat"
        }

        should("player main in competitive should be reflected") {
            player.mainComp shouldBe "ana"
        }
    }
}
