package com.example.cnb.bbvideo.net

import com.example.cnb.bbvideo.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * Created by cnb on 2017/12/1.
 */
class NetManager private constructor() {

    val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy { NetManager() }
    }


    /**
     * 发送网络请求
     */
    fun <RESPONSE> sendRequest(req: MRequest<RESPONSE>) {
        val request = Request
                .Builder()
                .url(req.url)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {

            //失败时返回的数据
            override fun onFailure(call: Call?, e: IOException?) {
                //由于在子线程中更新线程会报错，所以要用主线程去刷新界面
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //隐藏刷新控件
                        req.handler.onEror(e?.message)
//                         refreshLayout.isRefreshing = false
                    }
                })
            }

            //成功返回的数据
            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        req.handler.onSuccess(parseResult)
                    }
                })
            }
        })

    }
}