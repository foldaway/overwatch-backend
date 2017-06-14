package me.duncanleo.overwatchdashboard.web

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing

/**
 * Created by duncanleo on 14/6/17.
 */

fun StartServer() {
    embeddedServer(Jetty, 8080) {
        routing {
            get("/") {
                call.respondText("Hello!")
            }
        }
    }.start(wait = true)
}