package me.duncanleo.overwatchdashboard.web

import freemarker.template.Configuration
import me.duncanleo.overwatchdashboard.data
import spark.ModelAndView
import spark.Service.ignite
import spark.template.freemarker.FreeMarkerEngine
import java.io.File

/**
 * Created by duncanleo on 14/6/17.
 */

fun StartServer() {
    val http = ignite()
    http.port(8080)
    http.staticFiles.externalLocation("static")
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