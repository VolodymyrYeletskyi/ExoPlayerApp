package com.yeletskyiv.exoplayerapp.di.module

import androidx.room.Room
import com.yeletskyiv.exoplayerapp.App
import com.yeletskyiv.exoplayerapp.room.dao.VideoDao
import com.yeletskyiv.exoplayerapp.room.database.VideoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class DatabaseModule(private val app: App) {

    @Singleton
    @Provides
    fun provideDao(): VideoDao {
        return Room.databaseBuilder(app, VideoDatabase::class.java, "videos").build().videoDao()
    }
}