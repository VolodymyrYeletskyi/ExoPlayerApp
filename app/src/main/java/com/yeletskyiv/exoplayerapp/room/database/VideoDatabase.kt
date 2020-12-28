package com.yeletskyiv.exoplayerapp.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yeletskyiv.exoplayerapp.room.dao.VideoDao
import com.yeletskyiv.exoplayerapp.room.entity.Video

@Database(entities = [Video::class], version = 1)
abstract class VideoDatabase : RoomDatabase(){

    abstract fun videoDao(): VideoDao
}