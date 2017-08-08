package me.duncanleo.overwatchdashboard.tests

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import me.duncanleo.overwatchdashboard.model.*
import me.duncanleo.overwatchdashboard.network.model.PlayerProfileResponse
import me.duncanleo.overwatchdashboard.network.model.PlayerStatsResponse
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
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

        val fakeStatsResponse = PlayerStatsResponse(
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
        )

        val fakeProfileResponse = PlayerProfileResponse(
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

        // Connect to in-memory driver
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

        var playerCount = 0

        transaction {
            create(Players, PlayersData, Heroes)

            Player.findOrInsert({ Players.battleTag eq "someone-12345" }, {
                battleTag = "someone-12345"
                playerIcon = "fuck"
                platform = "pc"
                region = "us"
            })

            playerCount = Players.selectAll().count()
        }

        should("only one row created for same user") {
             playerCount shouldBe 1
        }

    }
}
