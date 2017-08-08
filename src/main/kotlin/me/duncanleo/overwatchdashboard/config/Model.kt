package me.duncanleo.overwatchdashboard.config

import com.natpryce.konfig.*

object database : PropertyGroup() {
    val name by stringType
    val host by stringType
    val port by intType
    val user by stringType
    val password by stringType
    val ssl by booleanType
}
