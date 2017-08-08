package me.duncanleo.overwatchdashboard.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

/**
 * Created by duncanleo on 8/8/17.
 */
class Hero(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Hero>(Heroes)

    var name by Heroes.name
    var img by Heroes.img

    val qpMains by PlayerData referrersOn PlayersData.mainQP
    val compMains by PlayerData referrersOn PlayersData.mainComp
}
