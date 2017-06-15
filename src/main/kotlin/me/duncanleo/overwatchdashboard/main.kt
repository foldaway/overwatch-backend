package me.duncanleo.overwatchdashboard

import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import me.duncanleo.overwatchdashboard.network.Network
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse
import me.duncanleo.overwatchdashboard.web.StartServer
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

/**
 * Created by duncanleo on 14/6/17.
 */

val data = mutableMapOf<String, UserStatsResponse>()

fun main(args: Array<String>) {
    // Periodically get updated data
    val moshi = Moshi.Builder().build()
    val tags = moshi.adapter(Array<String>::class.java).fromJson(File("tags.json").readText())
    if (tags == null) {
        print("Could not read tags.json")
        return
    }

    timer(name = "data-fetcher", initialDelay = 0, period = TimeUnit.HOURS.toMillis(1)) {
        for (tag in tags) {
            Network.owAPIService.getUserStats(tag)
                    .observeOn(Schedulers.newThread())
                    .subscribe({ response ->
                        data[tag] = response
                    }, { error ->
                        data.remove(tag)
                        error.printStackTrace()
                    })
        }
    }

    StartServer()
}