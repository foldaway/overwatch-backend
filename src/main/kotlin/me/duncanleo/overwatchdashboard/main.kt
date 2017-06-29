package me.duncanleo.overwatchdashboard

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import me.duncanleo.overwatchdashboard.model.Player
import me.duncanleo.overwatchdashboard.model.Tag
import me.duncanleo.overwatchdashboard.network.Network
import me.duncanleo.overwatchdashboard.web.StartServer
import org.slf4j.event.Level
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

/**
 * Created by duncanleo on 14/6/17.
 */

val data = mutableMapOf<String, Player>()

fun main(args: Array<String>) {
    // Periodically get updated data
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()
    val tags = moshi.adapter(Array<Tag>::class.java).fromJson(File("tags.json").readText())
    if (tags == null) {
        print("Could not read tags.json")
        return
    }

    timer(name = "data-fetcher", initialDelay = 0, period = TimeUnit.HOURS.toMillis(1)) {
        println("[TIMER] Fetching all data")
        Observable.fromArray(*tags)
                .delay(4, TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .doOnNext {
                    println("[TIMER] Fetching stats for '$it'")
                }
                .flatMap({
                    Network.nodeOWAPIService.getPlayerStats(platform = it.platform, region = it.region, battleTag = it.tag)
                            .toObservable()
                }, { tag, statsRes ->
                    Pair(tag, statsRes)
                })
                .doOnNext {
                    println("[TIMER] Fetching heroes for '${it.first}'")
                }
                .flatMap({
                    Network.nodeOWAPIService.getPlayerHeroes(platform = it.first.platform, region = it.first.region, battleTag = it.first.tag)
                            .toObservable()
                }, { (first, second), heroesRes ->
                    Player(first.tag, first.platform, first.region, second, heroesRes)
                })
                .retryWhen {
                    it.doOnNext {
                        it.printStackTrace()
                        println("[TIMER] Retry.")
                    }.delay(5, TimeUnit.SECONDS)
                }
                .subscribe({ player ->
                    println("[TIMER] Completed fetching for '${player.battleTag}'")
                    data[player.battleTag] = player
                }, { error ->
//                    data.remove( /)
                    error.printStackTrace()
                })
    }

    StartServer()
}