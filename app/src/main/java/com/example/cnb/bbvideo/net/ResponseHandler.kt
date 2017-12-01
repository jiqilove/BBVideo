package com.example.cnb.bbvideo.net

/**
 * Created by cnb on 2017/12/1.
 */
interface ResponseHandler<RESPONSE> {
    fun onEror(msg:String?)
    fun  onSuccess(result: Any?)

}