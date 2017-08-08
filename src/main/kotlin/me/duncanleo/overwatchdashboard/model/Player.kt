package me.duncanleo.overwatchdashboard.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

/**
 * Created by duncanleo on 7/8/17.
 */
class Player(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Player>(Players)

    var battleTag by Players.battleTag
    var playerIcon by Players.playerIcon
    var platform by Players.platform
    var region by Players.region

    val datas by PlayerData referrersOn PlayersData.player
}
