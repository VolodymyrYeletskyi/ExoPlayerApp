package com.yeletskyiv.exoplayerapp

import android.app.Application
import com.yeletskyiv.exoplayerapp.di.module.NetworkModule
import com.yeletskyiv.exoplayerapp.di.DaggerAppComponent
import com.yeletskyiv.exoplayerapp.di.module.DatabaseModule

class App : Application() {

    val daggerAppComponent = DaggerAppComponent.builder()
        .networkModule(NetworkModule())
        .databaseModule(DatabaseModule(this))
        .build()
}