package me.duncanleo.overwatchdashboard.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by duncanleo on 14/6/17.
 */
object Network {
    val logger = HttpLoggingInterceptor()
    get() {
        field.level = HttpLoggingInterceptor.Level.BASIC
        return field
    }

    val moshiConverter
    get() = {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()
        MoshiConverterFactory.create(moshi)
    }

    val okHttpClient
    get() = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

    val nodeOWAPIService
    get() = Retrofit.Builder()
            .baseUrl("https://ow-api.herokuapp.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(moshiConverter())
            .build()
            .create(NodeOWAPIService::class.java)
}