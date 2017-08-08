package me.duncanleo.overwatchdashboard.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

/**
 * Created by duncanleo on 7/8/17.
 */
class PlayerData(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlayerData>(PlayersData)

    var player by Player referencedOn PlayersData.player
    var level by PlayersData.level
    var sr by PlayersData.sr
    var date by PlayersData.date

    var mainQP by Hero referencedOn PlayersData.mainQP
    var mainComp by Hero referencedOn PlayersData.mainComp
}
