package com.example.cnb.bbvideo.presenter.impl

import com.example.cnb.bbvideo.net.HomeRequest
import com.example.cnb.bbvideo.net.ResponseHandler
import com.example.cnb.bbvideo.presenter.interf.HomePresenter
import com.example.cnb.bbvideo.util.ThreadUtil
import com.example.cnb.bbvideo.util.URLProviderUtils
import com.example.cnb.bbvideo.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean

import okhttp3.*
import java.io.IOException


/**
 * Created by cnb on 2017/11/30.
 */
class HomePresenterImpl(var  homeView: HomeView) :HomePresenter{

    /**
     * 初始化数据、或者是更新数据
     */
    override fun loadDatas() {
//定义一个request
        val request=HomeRequest(0,object :ResponseHandler<List<HomeRequest>>{
            override fun onEror(msg: String?) {

            }

            override fun onSuccess(result: Any?) {

            }

        })


      /*
        val path = URLProviderUtils.getHomeUrl(0, 5)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {

            //失败时返回的数据
            override fun onFailure(call: Call?, e: IOException?) {
                //由于在子线程中更新线程会报错，所以要用主线程去刷新界面
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                      homeView.onError(e?.message)
//                         refreshLayout.isRefreshing = false
                    }
                })
            }

            //成功返回的数据
            override fun onResponse(call: Call?, response: Response?) {

                val result = response?.body()?.string()
                val gson = Gson()
                var list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {
                }.type)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //将结果回调
                        homeView.loadSuccess(list)
//                        adpter.updataList(list)
//                        refreshLayout.isRefreshing = false
                    }
                })
            }
        })

        */
    }

    override fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 5)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {

            //失败时返回的数据
            override fun onFailure(call: Call?, e: IOException?) {
                //由于在子线程中更新线程会报错，所以要用主线程去刷新界面
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeView.onError(e?.message)
                        //隐藏刷新控件
//                        println("fdsfdsaf")
//                        refreshLayout.isRefreshing = false
                    }
                })
            }

            //成功返回的数据
            override fun onResponse(call: Call?, response: Response?) {

                val result = response?.body()?.string()
                val gson = Gson()
                var list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {
                }.type)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {

                        homeView.loadMore(list)

//                        adpter.loadMore(list)
//                        refreshLayout.isRefreshing = false
                    }
                })
            }
        })
    }


}