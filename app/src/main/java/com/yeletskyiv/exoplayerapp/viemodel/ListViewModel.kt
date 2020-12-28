package com.yeletskyiv.exoplayerapp.viemodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeletskyiv.exoplayerapp.R
import com.yeletskyiv.exoplayerapp.room.dao.VideoDao
import com.yeletskyiv.exoplayerapp.room.entity.Video
import com.yeletskyiv.exoplayerapp.retrofit.VideoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject lateinit var videoApi: VideoApi

    @Inject lateinit var dao: VideoDao

    private val videoNames = mutableListOf<String>()

    val singleVideoData: MutableLiveData<Video> = MutableLiveData()

    val manyVideoData: MutableLiveData<List<Video>> = MutableLiveData()

    fun downloadVideos(context: Context) = viewModelScope.launch(Dispatchers.Default) {
        val videos = dao.getAllVideos()
        if (videos.isEmpty()) {
            initVideoNames(context)
            for (ref in videoNames) {
                val response = videoApi.getVideos(ref)
                if (response.isSuccessful) {
                    val bytes = viewModelScope.async(Dispatchers.IO) { response.body()?.bytes() }
                    val dir = context.cacheDir
                    val file = File(dir.absolutePath + "/" + ref + ".txt")

                    viewModelScope.launch(Dispatchers.Unconfined) {
                        bytes.await()?.let { file.writeBytes(it) }
                        val video = Video(ref, file.absolutePath)
                        dao.insertVideo(video)
                        singleVideoData.postValue(video)
                    }.join()
                }
                else {
                    throw IllegalArgumentException("Illegal argument exception")
                }
            }
            manyVideoData.postValue(dao.getAllVideos())
        }
        else manyVideoData.postValue(videos)
    }

    private fun initVideoNames(context: Context) {
        videoNames.add(context.getString(R.string.video1) + ".mp4")
        videoNames.add(context.getString(R.string.video2) + ".mp4")
        videoNames.add(context.getString(R.string.video3) + ".mp4")
        videoNames.add(context.getString(R.string.video4) + ".mp4")
        videoNames.add(context.getString(R.string.video5) + ".mp4")
        videoNames.add(context.getString(R.string.video6) + ".mp4")
        videoNames.add(context.getString(R.string.video7) + ".mp4")
        videoNames.add(context.getString(R.string.video8) + ".mp4")
        videoNames.add(context.getString(R.string.video9) + ".mp4")
        videoNames.add(context.getString(R.string.video10) + ".mp4")
        videoNames.add(context.getString(R.string.video11) + ".mp4")
        videoNames.add(context.getString(R.string.video12) + ".mp4")
        videoNames.add(context.getString(R.string.video13) + ".mp4")
    }
}