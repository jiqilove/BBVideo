package com.example.cnb.bbvideo.view

import com.itheima.player.model.bean.VideoAreaBean

/**
 * Created by cnb on 2017/12/5.
 */
interface VideoView {
    fun onSuccess(result: List<VideoAreaBean>)

    fun onError(msg: String?) {}
}