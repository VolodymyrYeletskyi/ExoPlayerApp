package com.yeletskyiv.exoplayerapp.ui

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.yeletskyiv.exoplayerapp.App
import com.yeletskyiv.exoplayerapp.R
import com.yeletskyiv.exoplayerapp.viemodel.VideoViewModel
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer
    private lateinit var playerNotificationManager: PlayerNotificationManager

    private val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        (application as App).daggerAppComponent.inject(videoViewModel)

        val intent = intent
        val position = intent.getIntExtra("position", 0)
        val videoNumbers = intent.getIntegerArrayListExtra("video_number")

        playerNotificationManager = PlayerNotificationManager.createWithNotificationChannel(
            this,
            getString(R.string.channel_name),
            R.string.channel_name,
            R.string.channel_description,
            1234,
            object : PlayerNotificationManager.MediaDescriptionAdapter {

                override fun getCurrentContentTitle(player: Player): CharSequence {
                    return getString(R.string.player)
                }

                override fun createCurrentContentIntent(player: Player): PendingIntent? {
                    return null
                }

                override fun getCurrentContentText(player: Player): CharSequence? {
                    return getString(R.string.video) + videoNumbers?.get(player.currentWindowIndex)
                }

                override fun getCurrentLargeIcon(
                    player: Player,
                    callback: PlayerNotificationManager.BitmapCallback
                ): Bitmap? {
                    return null
                }
            }
        )
        player = SimpleExoPlayer.Builder(this).build()
        videoViewModel.showVideo()
        videoViewModel.videoData.observe(this) {
            player.setMediaItems(it)
        }
        player.seekTo(position, 0)

        playerNotificationManager.setPlayer(player)
        playerView.player = player
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        playerNotificationManager.setPlayer(null)
        player.release()
        super.onDestroy()
    }
}