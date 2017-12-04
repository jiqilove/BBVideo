package com.example.cnb.bbvideo.presenter.interf

/**
 * Created by cnb on 2017/11/30.
 */
interface HomePresenter {
 companion object {
     val TYPE_INIT_OR_REFRESH = 1//第一次加载或者刷新
     val TYPE_LOAD_MORE = 2//加载更多
 }

    fun loadDatas()
    fun loadMore(offset: Int)
}