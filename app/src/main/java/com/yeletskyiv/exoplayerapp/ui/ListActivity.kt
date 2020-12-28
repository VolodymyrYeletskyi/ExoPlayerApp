package com.yeletskyiv.exoplayerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.yeletskyiv.exoplayerapp.App
import com.yeletskyiv.exoplayerapp.recyclerview.CellClickListener
import com.yeletskyiv.exoplayerapp.viemodel.ListViewModel
import com.yeletskyiv.exoplayerapp.R
import com.yeletskyiv.exoplayerapp.recyclerview.VideoAdapter
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity(), CellClickListener {

    private val videoAdapter = VideoAdapter(this)

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).daggerAppComponent.inject(listViewModel)

        listViewModel.downloadVideos(applicationContext)
        listViewModel.manyVideoData.observe(this) {
            videoAdapter.setElements(it)
        }
        listViewModel.singleVideoData.observe(this) {
            videoAdapter.addElement(it)
        }
        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        videoRv.addItemDecoration(divider)
        videoRv.adapter = videoAdapter
    }

    override fun onCellClickListener(position: Int) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("position", position)
        intent.putIntegerArrayListExtra("video_number", arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
        startActivity(intent)
    }
}