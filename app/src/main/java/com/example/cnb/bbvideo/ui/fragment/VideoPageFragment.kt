package com.example.cnb.bbvideo.ui.fragment

import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.adpter.VideoListAdapter
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.base.BaseListApdater
import com.example.cnb.bbvideo.base.BaseListFragment
import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.model.VideoPlayBean
import com.example.cnb.bbvideo.presenter.impl.VideoListPresentrImpl
import com.example.cnb.bbvideo.ui.activity.IjkVideoPlayerActivity
import com.example.cnb.bbvideo.ui.activity.JiecaoVideoPlayerActivity
import com.example.cnb.bbvideo.ui.activity.VideoPlayerActivity
import com.example.cnb.bbvideo.view.VideoListView
import com.example.cnb.bbvideo.widget.VideoItemView

import com.itheima.player.model.bean.VideoPagerBean
import com.itheima.player.model.bean.VideosBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by cnb on 2017/12/5.
 */
class VideoPageFragment : BaseListFragment<VideoPagerBean, VideosBean, VideoItemView>(), VideoListView {
    var code: String? = null

    override fun init() {
        code = arguments.getString("args")
    }

    override fun getSpecialAdpater(): BaseListApdater<VideosBean, VideoItemView> {
        return VideoListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return VideoListPresentrImpl(code!!, this)
    }

    override fun getList(response: VideoPagerBean?): List<VideosBean>? {
        return response?.videos
    }


    override fun initListener() {
        super.initListener()
        //设置条目的监听事件
        adapter.setMyListener{

            val videoPlayBean= VideoPlayBean(it.id,it.title,it.url)
            //            a->Unit
//            println("it=$it")
            startActivity<JiecaoVideoPlayerActivity>("item" to videoPlayBean)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ij_video.stopPlayback()
    }

}