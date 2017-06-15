package me.duncanleo.overwatchdashboard

import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.network.Network
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse
import me.duncanleo.overwatchdashboard.web.StartServer
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

/**
 * Created by duncanleo on 14/6/17.
 */

val data = mutableMapOf<String, Player>()

fun main(args: Array<String>) {
    // Periodically get updated data
    val moshi = Moshi.Builder().build()
    val tags = moshi.adapter(Array<String>::class.java).fromJson(File("tags.json").readText())
    if (tags == null) {
        print("Could not read tags.json")
        return
    }

    timer(name = "data-fetcher", initialDelay = 0, period = TimeUnit.HOURS.toMillis(1)) {
        Observable.fromArray(*tags)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .flatMap({
                    Network.owAPIService.getUserStats(it)
                            .toObservable()
                }, { tag, statsRes ->
                    Pair(tag, statsRes)
                })
                .flatMap({
                    Network.owAPIService.getUserHeroes(it.first).toObservable()
                }, { (first, second), heroesRes ->
                    Player(first, second, heroesRes)
                })
                .subscribe({ player ->
                    data[player.battleTag] = player
                }, { error ->
//                    data.remove( /)
                    error.printStackTrace()
                })
    }

    StartServer()
}