package com.example.cnb.bbvideo.net

import com.example.cnb.bbvideo.util.URLProviderUtils
import com.itheima.player.model.bean.VideoAreaBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoAreaRequest(handler: ResponseHandler<List<VideoAreaBean>>)
    :MRequest<List<VideoAreaBean>>(0,URLProviderUtils.getVideoAreaUrl(), handler) {


}