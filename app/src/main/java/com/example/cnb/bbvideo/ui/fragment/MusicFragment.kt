package com.example.cnb.bbvideo.ui.fragment


import com.example.cnb.bbvideo.adpter.MusicAdapter
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.base.BaseListFragment
import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.presenter.impl.MusicPresenterImpl
import com.example.cnb.bbvideo.widget.MusicItemView
import com.itheima.player.model.bean.MusicBean

/**
 * Created by cnb on 2017/11/28.
 */
class MusicFragment :BaseListFragment<MusicBean,MusicBean.PlayListsBean , MusicItemView>(){


    override fun getSpecialAdpater(): BaseListApdater<MusicBean.PlayListsBean, MusicItemView> {
return  MusicAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
return  MusicPresenterImpl(this)
          }

    override fun getList(response: MusicBean?): List<MusicBean.PlayListsBean>? {
        return  response?.playLists   }

}