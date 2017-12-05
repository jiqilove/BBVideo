package com.example.cnb.bbvideo.presenter.impl

import com.example.cnb.bbvideo.base.BaseListPresenter
import com.example.cnb.bbvideo.base.BaseView
import com.example.cnb.bbvideo.net.HomeRequest
import com.example.cnb.bbvideo.net.ResponseHandler
import com.example.cnb.bbvideo.presenter.interf.HomePresenter
import com.itheima.player.model.bean.HomeItemBean


/**
 * Created by cnb on 2017/11/30.
 */
class HomePresenterImpl(var homeView: BaseView<List<HomeItemBean>>?) : HomePresenter, ResponseHandler<List<HomeItemBean>> {
    override fun onError(type: Int, msg: String?) {

        homeView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<HomeItemBean>) {

        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) {
            homeView?.loadSuccess(result)
        } else if (type == BaseListPresenter.TYPE_LOAD_MORE) {
            homeView?.loadMore(result)
        }
    }

    /**
     * 解绑view 和 presenter
     * */
    override fun destroyView() {
        if (homeView != null) {
            homeView = null
        }
    }


    /**
     * 初始化数据、或者是更新数据
     */
    override fun loadDatas() {
        //定义一个request
        val request = HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).excute()
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
        //定义一个request
        val request = HomeRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).excute()
        /*
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
        */
    }


}