package com.example.cnb.bbvideo.presenter.impl

import com.example.cnb.bbvideo.net.ResponseHandler
import com.example.cnb.bbvideo.net.VideoAreaRequest
import com.example.cnb.bbvideo.presenter.interf.VideoPresenter
import com.example.cnb.bbvideo.view.VideoView
import com.itheima.player.model.bean.VideoAreaBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoPresenterImpl (var videoView: VideoView): VideoPresenter, ResponseHandler<List<VideoAreaBean>> {
    override fun onError(type: Int, msg: String?) {
        videoView.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<VideoAreaBean>) {
        videoView.onSuccess(result)
    }


    fun loadDatas() {
VideoAreaRequest(this).excute()
    }

}