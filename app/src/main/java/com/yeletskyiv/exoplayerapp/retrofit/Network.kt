package com.yeletskyiv.exoplayerapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    fun createVideoApiService(): VideoApi {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(VideoApi::class.java)
    }

    companion object {

        const val BASE_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"
    }
}