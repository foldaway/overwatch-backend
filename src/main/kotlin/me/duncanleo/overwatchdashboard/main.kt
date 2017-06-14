package me.duncanleo.overwatchdashboard

import io.reactivex.schedulers.Schedulers
import me.duncanleo.overwatchdashboard.network.Network
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse
import me.duncanleo.overwatchdashboard.web.StartServer

/**
 * Created by duncanleo on 14/6/17.
 */

val data = mutableMapOf<String, UserStatsResponse>()

fun main(args: Array<String>) {
    // Periodically get updated data

    val tags = listOf("Dad-12262")
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

    StartServer()
}