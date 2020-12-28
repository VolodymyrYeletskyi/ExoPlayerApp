package com.yeletskyiv.exoplayerapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yeletskyiv.exoplayerapp.room.entity.Video

@Dao
interface VideoDao {

    @Query("SELECT * FROM video")
    fun getAllVideos(): List<Video>

    @Insert
    fun insertVideo(video: Video)
}