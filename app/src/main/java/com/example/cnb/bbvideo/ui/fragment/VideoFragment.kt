package com.example.cnb.bbvideo.ui.fragment


import com.example.cnb.bbvideo.R

import com.example.cnb.bbvideo.adpter.VideoAreaAdapter
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.presenter.impl.VideoPresenterImpl
import com.example.cnb.bbvideo.view.VideoView
import com.itheima.player.model.bean.VideoAreaBean
import kotlinx.android.synthetic.main.fragment_video.*

/**
 * Created by cnb on 2017/11/28.
 */
class VideoFragment : BaseFragment(), VideoView {
    override fun getLayoutId(): Int = R.layout.fragment_video
    val presenter by lazy { VideoPresenterImpl(this) }


    override fun onError(msg: String?) {
        print("失败")
    }

    override fun onSuccess(result: List<VideoAreaBean>) {
        //zai fragment 中管理fragment
        val adapter = VideoAreaAdapter(context,result, childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


    override fun initListener() {


    }

    override fun initData() {

        //加载数据

        presenter.loadDatas()

    }
}