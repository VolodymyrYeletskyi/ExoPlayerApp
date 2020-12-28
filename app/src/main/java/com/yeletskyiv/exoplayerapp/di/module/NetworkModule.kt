package com.yeletskyiv.exoplayerapp.di.module

import com.yeletskyiv.exoplayerapp.retrofit.Network
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class NetworkModule {

    @Singleton
    @Provides
    fun provideVideoApi() = Network().createVideoApiService()
}