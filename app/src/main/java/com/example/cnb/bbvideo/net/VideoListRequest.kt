package com.example.cnb.bbvideo.net

import com.example.cnb.bbvideo.util.URLProviderUtils
import com.itheima.player.model.bean.VideoPagerBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoListRequest (type :Int ,code:String,offset:Int,handler: ResponseHandler<VideoPagerBean>) :
        MRequest<VideoPagerBean>(type,URLProviderUtils.getMVListUrl(code,offset,5),handler) {
}