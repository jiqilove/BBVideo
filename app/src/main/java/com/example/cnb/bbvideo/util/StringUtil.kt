package com.example.cnb.bbvideo.util

/**
 * Created by cnb on 2017/12/10.
 */
object StringUtil {

    val SEC = 1000
    val MIN = 60 * 1000
    val HOUR = 60 * 60*1000


    fun parseDuratoin(progress: Int): String {

        val hour = progress / HOUR
        val min = progress % HOUR / MIN
        val sec = progress % MIN / SEC
        var result: String  = ""
        //不足1小时不显示小时
        if(hour==0){
            //不住1小时
            result=  String.format("%02d:%02d",min,sec)
        }
        else {
            result=   String.format("%02d:%02d:%02d",hour,min,sec)
        }

        return  result

    }

}