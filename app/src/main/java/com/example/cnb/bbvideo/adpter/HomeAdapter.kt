package com.example.cnb.bbvideo.adpter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.widget.HomeItemView
import com.example.cnb.bbvideo.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

/**
 * Created by cnb on 2017/11/30.
 */
class HomeAdapter : BaseListApdater<HomeItemBean, HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

}