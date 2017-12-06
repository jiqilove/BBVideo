package com.example.cnb.bbvideo.ui.activity

import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*


/**
 * ClassName:VideoPlayerActivity
 * Description:
 */
class IjkVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_ijk
    }

    override fun initData() {
        //获取传递的数据
        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        ij_video.setVideoPath(videoPlayBean.url)
        ij_video.setOnPreparedListener {
            ij_video.start()
        }

    }

}