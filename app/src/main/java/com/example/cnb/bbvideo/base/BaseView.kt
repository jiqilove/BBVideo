package com.example.cnb.bbvideo.base

import com.itheima.player.model.bean.MusicBean

/**
 * Created by cnb on 2017/12/5.
 */

/**
 * 所有下拉刷新 和上啦加载的列表的  view界面
 */
interface BaseView <RESPONSE>{
/**
 * 获取数据失败
 */
fun onError(message: String?)

/**
 * 获取数据据成功
 */
fun loadSuccess(response: RESPONSE?)

/**
 * 加载更多数据成功
 */
fun loadMore(response: RESPONSE?)
}