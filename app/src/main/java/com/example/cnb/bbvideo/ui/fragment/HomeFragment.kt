package com.example.cnb.bbvideo.ui.fragment


import com.example.cnb.bbvideo.adpter.HomeAdapter
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.base.BaseListFragment
import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.presenter.impl.HomePresenterImpl
import com.example.cnb.bbvideo.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean


/**
 * Created by cnb on 2017/11/28.
 */
class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {
    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }

    override fun getSpecialAdpater(): BaseListApdater<HomeItemBean, HomeItemView> {
        return HomeAdapter()

    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }


}