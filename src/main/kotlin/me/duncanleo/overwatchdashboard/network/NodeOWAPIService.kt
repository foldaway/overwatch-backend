package me.duncanleo.overwatchdashboard.network

import io.reactivex.Single
import me.duncanleo.overwatchdashboard.network.model.PlayerProfileResponse
import me.duncanleo.overwatchdashboard.network.model.PlayerStatsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Retrofit routes for OWAPI
 */
interface NodeOWAPIService {
    @Headers("Accept: application/json")
    @GET("stats/{platform}/{region}/{battletag}")
    fun getPlayerStats(@Path("platform") platform: String, @Path("region") region: String, @Path("battletag") battleTag: String): Single<PlayerStatsResponse>

    @Headers("Accept: application/json")
    @GET("profile/{platform}/{region}/{battletag}")
    fun getPlayerHeroes(@Path("platform") platform: String, @Path("region") region: String, @Path("battletag") battleTag: String): Single<PlayerProfileResponse>
}