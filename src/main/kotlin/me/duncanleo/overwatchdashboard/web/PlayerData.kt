package me.duncanleo.overwatchdashboard.web

import me.duncanleo.overwatchdashboard.model.Players
import me.duncanleo.overwatchdashboard.model.PlayersData
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

val playerDataRouteGroup = spark.RouteGroup {
    http.get(
            "",
            { req, res ->
                val playerId = req.queryParams("player_id")
                if (playerId.isNullOrEmpty() || playerId.toIntOrNull() == null) {
                    return@get res.status(400)
                }
                res.type(jsonMimeType)
                transaction {
                    (PlayersData innerJoin Players)
                            .select{ Players.id eq playerId.toInt() }
                            .map { mapOf(
                                    "level" to it[PlayersData.level],
                                    "sr" to it[PlayersData.sr],
                                    "date" to it[PlayersData.date]
//                                    "mainQP" to it[PlayersData.mainQP],
//                                    "mainComp" to it[PlayersData.mainComp]
                            ) }
                            .toList()
                }
            },
            transformer::render
    )
}
