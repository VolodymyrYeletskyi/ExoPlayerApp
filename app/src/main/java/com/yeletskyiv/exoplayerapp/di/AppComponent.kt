package com.yeletskyiv.exoplayerapp.di

import com.yeletskyiv.exoplayerapp.di.module.DatabaseModule
import com.yeletskyiv.exoplayerapp.di.module.NetworkModule
import com.yeletskyiv.exoplayerapp.viemodel.ListViewModel
import com.yeletskyiv.exoplayerapp.viemodel.VideoViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(listViewModel: ListViewModel)

    fun inject(videoViewModel: VideoViewModel)
}