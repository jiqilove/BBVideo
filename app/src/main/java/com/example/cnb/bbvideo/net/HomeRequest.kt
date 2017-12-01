package com.example.cnb.bbvideo.net

import com.example.cnb.bbvideo.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean

/**
 * Created by cnb on 2017/12/1.
 */
class  HomeRequest(offset:Int, handler: ResponseHandler<List<HomeRequest>>) :
        MRequest<List<HomeItemBean>>(URLProviderUtils.getHomeUrl(offset,20),handler){

}