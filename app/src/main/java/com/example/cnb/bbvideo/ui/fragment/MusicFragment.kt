package com.example.cnb.bbvideo.ui.fragment


import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager

import android.support.v7.widget.RecyclerView
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.adpter.MusicApdater
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.base.BaseListFragment
import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.presenter.impl.MusicPresenterImpl
import com.example.cnb.bbvideo.view.MusicView
import com.example.cnb.bbvideo.widget.MusicItemView
import com.itheima.player.model.bean.MusicBean
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by cnb on 2017/11/28.
 */
class MusicFragment :BaseListFragment<MusicBean,MusicBean.PlayListsBean , MusicItemView>(){


    override fun getSpecialAdpater(): BaseListApdater<MusicBean.PlayListsBean, MusicItemView> {
return  MusicApdater()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
return  MusicPresenterImpl(this)
          }

    override fun getList(response: MusicBean?): List<MusicBean.PlayListsBean>? {
        return  response?.playLists   }

}