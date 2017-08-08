package me.duncanleo.overwatchdashboard.model

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insertAndGetId

/**
 * Created by duncanleo on 7/8/17.
 */
object Players : IntIdTable() {
    val battleTag = varchar("battleTag", 30).uniqueIndex()
    val playerIcon = varchar("playerIcon", 100)
    val platform = varchar("platform", 10)
    val region = varchar("region", 10)
}

object PlayersData : IntIdTable() {
    val player = reference("player", Players)
    val level = integer("level")
    val sr = integer("sr")
    val date = datetime("date").uniqueIndex()
    val mainQP = reference("main_qp", Heroes)
    val mainComp = reference("main_comp", Heroes)
}

object Heroes: IntIdTable() {
    val name = varchar("name", 30).uniqueIndex()
    val img = varchar("img", 100)
}

fun <T: IntEntity> IntEntityClass<T>.findOrInsert(condition: SqlExpressionBuilder.() -> Op<Boolean>, insert: T.() -> Unit): T {
    val existingRow = this.find(condition)
    if (!existingRow.empty()) {
        return existingRow.first()
    }
    return this.new(insert)
}
