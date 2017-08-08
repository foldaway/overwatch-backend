package me.duncanleo.overwatchdashboard.model

import com.google.gson.annotations.Expose
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

/**
 * Created by duncanleo on 7/8/17.
 */
class Player(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Player>(Players)

    @delegate:Expose var battleTag by Players.battleTag
    @delegate:Expose var playerIcon by Players.playerIcon
    @delegate:Expose var platform by Players.platform
    @delegate:Expose var region by Players.region

    val datas by PlayerData referrersOn PlayersData.player
}
