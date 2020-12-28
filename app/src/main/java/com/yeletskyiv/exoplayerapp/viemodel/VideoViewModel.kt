package com.yeletskyiv.exoplayerapp.viemodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.MediaItem
import com.yeletskyiv.exoplayerapp.room.dao.VideoDao
import com.yeletskyiv.exoplayerapp.room.entity.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class VideoViewModel : ViewModel() {

    @Inject lateinit var dao: VideoDao

    private val videoItems = mutableListOf<MediaItem>()

    private val videos = mutableListOf<Video>()

    val videoData: MutableLiveData<List<MediaItem>> = MutableLiveData()

    fun showVideo() {
        runBlocking(Dispatchers.IO) { videos.addAll(dao.getAllVideos()) }
        for (video in videos) {
            videoItems.add(MediaItem.fromUri(video.videoPath))
        }
        videoData.value = videoItems
    }
}