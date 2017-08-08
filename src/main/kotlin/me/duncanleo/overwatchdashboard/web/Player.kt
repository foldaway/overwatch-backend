package me.duncanleo.overwatchdashboard.web

import me.duncanleo.overwatchdashboard.model.Player
import org.jetbrains.exposed.sql.transactions.transaction

val playerRouteGroup = spark.RouteGroup {
    http.get(
            "",
            { _, res ->
                res.type(jsonMimeType)
                transaction {
                    Player.all().toList()
                }
            },
            transformer::render
    )
}