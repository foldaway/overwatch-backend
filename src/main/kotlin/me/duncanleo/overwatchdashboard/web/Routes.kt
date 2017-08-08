package me.duncanleo.overwatchdashboard.web

import freemarker.template.Configuration
import me.duncanleo.overwatchdashboard.data
import me.duncanleo.overwatchdashboard.json.JsonTransformer
import spark.ModelAndView
import spark.Service.ignite
import spark.template.freemarker.FreeMarkerEngine
import java.io.File

/**
 * Created by duncanleo on 14/6/17.
 */

val http = ignite()
val transformer = JsonTransformer()
val jsonMimeType = "application/json"

fun StartServer() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080

    println("[WEB] Starting server on port $port")
    
    http.port(port)
    http.staticFiles.externalLocation("static")

    http.path("/api") {
        http.path("/players", playerRouteGroup)
        http.path("/playerdata", playerDataRouteGroup)
    }

    http.get("/") { _, _ ->
        val templateData = mutableMapOf(
                "battleTags" to data.keys,
                "data" to data
        )
        val config = Configuration()
        config.setDirectoryForTemplateLoading(File("static"))
        FreeMarkerEngine(config).render(
                ModelAndView(templateData, "index.ftl")
        )
    }
}