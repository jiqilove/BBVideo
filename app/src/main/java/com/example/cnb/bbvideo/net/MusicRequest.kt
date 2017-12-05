package com.example.cnb.bbvideo.net

import com.example.cnb.bbvideo.util.URLProviderUtils
import com.itheima.player.model.bean.MusicBean

/**
 * Created by cnb on 2017/12/4.
 */
class MusicRequest(type:Int,offset:Int,handler: ResponseHandler<MusicBean>)
    :MRequest<MusicBean>(type,URLProviderUtils.getMusicUrl(offset,5),handler) {



}