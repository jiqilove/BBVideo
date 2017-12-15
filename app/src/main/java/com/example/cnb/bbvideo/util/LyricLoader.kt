package com.example.cnb.bbvideo.util

import android.os.Environment
import android.util.Log
import java.io.File

/**
 * Created by cnb on 2017/12/14.
 */
object LyricLoader {
    //创建一个歌词文件
    val dir = File(Environment.getExternalStorageDirectory(), "/Downloads/lyric")



    fun loaderLyricFile(display_name: String): File {

        Log.e("dffdsafdsafd","$dir")

        return File(dir,display_name+".lrc")
    }
}