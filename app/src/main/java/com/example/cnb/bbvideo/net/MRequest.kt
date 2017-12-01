package com.example.cnb.bbvideo.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType


/**
 * Created by cnb on 2017/12/1.
 *
 * desc:  所有请求数据的基类
 */

open class MRequest<RESOPNSE>(val url:String, val handler: ResponseHandler<List<HomeRequest>>) {
    /**
     * 解析网络请求结果
     */
    fun parseResult(result: String?): RESOPNSE {
        val gson = Gson()
        //获取泛型类型
//        Type type=((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]   //java 代码
        val type = (this.javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[0]

        val list = gson.fromJson<RESOPNSE>(result, type)
        return  list
    }




}