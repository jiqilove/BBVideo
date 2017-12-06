package com.example.cnb.bbvideo.presenter.impl

import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.net.ResponseHandler
import com.example.cnb.bbvideo.net.VideoListRequest
import com.example.cnb.bbvideo.presenter.interf.VideoListPresenter
import com.example.cnb.bbvideo.view.VideoListView
import com.itheima.player.model.bean.VideoPagerBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoListPresentrImpl (var code: String, var videoListView: VideoListView?) : VideoListPresenter, ResponseHandler<VideoPagerBean> {
    override fun onError(type: Int, msg: String?) {
        print("BaseListPresenterImpl   错误")
    }

    override fun onSuccess(type: Int, result: VideoPagerBean) {

        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) {
            videoListView?.loadSuccess(result)
        } else {
            videoListView?.loadMore(result)
        }


    }

    override fun loadDatas() {
        VideoListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, code, 0, this).excute()
    }

    override fun loadMore(offset: Int) {
        VideoListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, offset, this).excute()
    }

    override fun destroyView() {
        if (videoListView != null) {
            videoListView = null
        }

    }
}