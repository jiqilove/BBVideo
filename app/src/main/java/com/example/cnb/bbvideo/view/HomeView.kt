package com.example.cnb.bbvideo.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * Created by cnb on 2017/11/30.
 */
/**
 * home界面和presenter 层交互
 *
 */
interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 获取数据据成功
     */
    fun loadSuccess(list: List<HomeItemBean>?)

    /**
     * 加载更多数据成功
     */
    fun loadMore(list: List<HomeItemBean>?)
}