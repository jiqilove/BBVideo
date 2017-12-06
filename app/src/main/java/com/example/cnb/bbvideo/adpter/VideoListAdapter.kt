package com.example.cnb.bbvideo.adpter

import android.content.Context
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.widget.VideoItemView
import com.itheima.player.model.bean.VideosBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoListAdapter :BaseListApdater<VideosBean,VideoItemView>(){
    override fun refreshView(itemView: VideoItemView, data: VideosBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): VideoItemView {
return  VideoItemView(context)
    }


}