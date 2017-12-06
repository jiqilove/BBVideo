package com.example.cnb.bbvideo.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.view.MusicView
import com.example.cnb.bbvideo.widget.HomeItemView
import com.example.cnb.bbvideo.widget.LoadMoreView
import com.example.cnb.bbvideo.widget.MusicItemView
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.model.bean.MusicBean

/**
 * Created by cnb on 2017/12/4.
 */
class MusicAdapter : BaseListApdater<MusicBean.PlayListsBean,MusicItemView>() {
    override fun getItemView(context: Context?): MusicItemView {
        return MusicItemView(context)
    }

    override fun refreshView(itemView: MusicItemView, data: MusicBean.PlayListsBean) {

itemView.setData(data)
          }


}