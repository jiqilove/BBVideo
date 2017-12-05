package com.example.cnb.bbvideo.presenter.impl

import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.base.BaseView
import com.example.cnb.bbvideo.net.MusicRequest
import com.example.cnb.bbvideo.net.ResponseHandler
import com.example.cnb.bbvideo.presenter.interf.MusicPresenter
import com.example.cnb.bbvideo.view.MusicView
import com.itheima.player.model.bean.MusicBean

/**
 * Created by cnb on 2017/12/4.
 */
class MusicPresenterImpl(var musicView: BaseView<MusicBean>?) : MusicPresenter, ResponseHandler<MusicBean> {
    override fun destroyView() {
        if(musicView!=null){
            musicView = null
        }
    }

    override fun onError(type: Int, msg: String?) {
        musicView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: MusicBean) {
        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) {
            musicView?.loadSuccess(result)
        }
        else if (type == BaseListPresenter.TYPE_LOAD_MORE) {
            musicView?.loadMore(result)
        }

    }

    override fun loadDatas() {
        MusicRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).excute()
    }

    override fun loadMore(offset: Int) {
        MusicRequest(BaseListPresenter.TYPE_LOAD_MORE,offset,this).excute()
    }

}