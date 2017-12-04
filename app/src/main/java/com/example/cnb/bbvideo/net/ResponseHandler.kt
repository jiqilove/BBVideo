package com.example.cnb.bbvideo.net

/**
 * Created by cnb on 2017/12/1.
 */
interface ResponseHandler<RESPONSE> {

    fun onError(type:Int,msg:String?)
    fun  onSuccess(type:Int,result: RESPONSE)

}