package me.duncanleo.overwatchdashboard.tests

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.network.model.UserHeroesResponse
import me.duncanleo.overwatchdashboard.network.model.UserStat
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse
import org.junit.runner.RunWith

/**
 * Created by duncanleo on 19/6/17.
 */

@RunWith(KTestJUnitRunner::class)
class PlayerTest : ShouldSpec() {
    init {
        val player = Player(
                "someone-12345",
                stats = UserStatsResponse(
                        korea = null,
                        europe = null,
                        usa = UserStatsResponse.Region(
                                UserStatsResponse.Region.Stats(
                                        competitive = UserStat(overallStats = mapOf(
                                                "comprank" to 1234.0,
                                                "avatar" to "https://example.com/avatar.jpg"
                                        ), averageStats = mapOf(), competitive = true, gameStats = mapOf()),
                                        quickplay = null
                                )
                        ),
                        any = null
                ),
                heroes = UserHeroesResponse(
                        korea = null,
                        europe = null,
                        usa = UserHeroesResponse.Region(
                                UserHeroesResponse.Region.HeroesContainer(
                                        playtime = UserHeroesResponse.Region.HeroesContainer.Playtime(
                                                competitive = mapOf("ana" to 1.0, "mercy" to 0.1),
                                                quickplay = mapOf("junkrat" to 1.0, "lucio" to 0.3)
                                        ),
                                        stats = UserHeroesResponse.Region.HeroesContainer.Stats(
                                                competitive = mapOf(),
                                                quickplay = mapOf()
                                        )
                                )
                        ),
                        any = null
                )
        )

        should("player season rating should return competitive SR") {
            player.seasonRating shouldBe 1234.0
        }

        should("player avatar should be reflected") {
            player.avatar shouldBe "https://example.com/avatar.jpg"
        }

        should("player main in QP should be reflected") {
            player.mainQP shouldBe "junkrat"
        }

        should("player main in competitive should be reflected") {
            player.mainComp shouldBe "mercy"
        }
    }
}
