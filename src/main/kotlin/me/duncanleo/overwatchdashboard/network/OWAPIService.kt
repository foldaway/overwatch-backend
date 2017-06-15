package me.duncanleo.overwatchdashboard.network

import io.reactivex.Single
import me.duncanleo.overwatchdashboard.network.model.UserHeroesResponse
import me.duncanleo.overwatchdashboard.network.model.UserStatsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Retrofit routes for OWAPI
 */
interface OWAPIService {
    @Headers("Accept: application/json")
    @GET("u/{battletag}/stats")
    fun getUserStats(@Path("battletag") battleTag: String): Single<UserStatsResponse>

    @GET("u/{battletag}/achievements")
    fun getUserAchievements(@Path("battletag") battleTag: String): Single<String>

    @GET("u/{battletag}/heroes")
    fun getUserHeroes(@Path("battletag") battleTag: String): Single<UserHeroesResponse>
}