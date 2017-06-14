package me.duncanleo.overwatchdashboard.web

import freemarker.cache.StringTemplateLoader
import me.duncanleo.overwatchdashboard.data
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.freemarker.FreeMarker
import org.jetbrains.ktor.freemarker.FreeMarkerContent
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing
import java.io.File

/**
 * Created by duncanleo on 14/6/17.
 */

fun StartServer() {
    embeddedServer(Jetty, 8080) {
        install(FreeMarker) {
            templateLoader = StringTemplateLoader().apply {
                putTemplate("index.ftl", File("static/index.ftl").readText())
            }
        }

        routing {
            get("/") {
                val templateData = mapOf(
                        "keys" to data.keys,
                        "data" to data.mapValues { mapOf(
                                "sr" to (it.value.usa?.stats?.competitive?.overallStats?.get("comprank") ?: "NA")
                        ) }
                )
                call.respond(FreeMarkerContent("index.ftl", templateData, etag = ""))
            }
        }
    }.start(wait = true)
}