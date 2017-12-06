package com.example.cnb.bbvideo.ui.activity


import android.support.v7.widget.Toolbar
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.model.VideoPlayBean
import com.example.cnb.bbvideo.util.ToolBarManager

import com.itheima.player.model.bean.VideoPagerBean
import kotlinx.android.synthetic.main.atcivity_videoplayer.*
import org.jetbrains.anko.find


/**
 * Created by cnb on 2017/11/28.
 */
class VideoPlayerActivity : BaseActivity() {

    override fun getLayoutId(): Int {

        return R.layout.atcivity_videoplayer

    }


    override fun initData() {
//获取传递的数据
        val videoPlayBean=intent.getParcelableExtra<VideoPlayBean>("item")

        videoView.setVideoPath(videoPlayBean.url)
        videoView.setOnPreparedListener {
            videoView.start()
        }

    }


}