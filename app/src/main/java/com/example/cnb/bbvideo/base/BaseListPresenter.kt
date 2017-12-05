package com.example.cnb.bbvideo.base

/**
 * Created by cnb on 2017/12/5.
 */

/**
 *  所有下拉刷新 和上啦加载的列表的  presenter 基类
 */
interface BaseListPresenter {
    companion object {
        val TYPE_INIT_OR_REFRESH = 1//第一次加载或者刷新
        val TYPE_LOAD_MORE = 2//加载更多
    }

    fun loadDatas()
    fun loadMore(offset: Int)
    fun destroyView()
}