package com.example.cnb.bbvideo.util

import com.itheima.player.model.LyricBean
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Created by cnb on 2017/12/14.
 */
object LyricUtil {

    //    解析文件
    fun pareLyric(file: File): List<LyricBean> {
        val list = ArrayList<LyricBean>()
        if (!file.exists()) {
            //判断歌词是否存在
            list.add(LyricBean(0, "歌词加载错误"))
            return list
        }
        //解析歌词文件，添加到集合中
//        val bfr = BufferedReader(InputStreamReader(FileInputStream(file), "gbk"))
//        var line = bfr.readLine()
//        while (line != null) {
//            line = bfr.readLine()
//        }
        val lineList = file.readLines(Charset.forName("gbk")) //读取歌词文件  返回每一行数据集合
        for (line in lineList) {
            //解析一行
            val lineList: List<LyricBean> = pareLine(line)
            //添加到集合中
            list.addAll(lineList)
        }
        list.sortBy { it.startTime }
        return list
    }


    //解析一行歌词
    private fun pareLine(line: String): List<LyricBean> {

        //创建集合
        val list = ArrayList<LyricBean>()
        //解析当前行
        val arr = line.split("]")

        //返回集合
        val content = arr.get(arr.size - 1)

        for (index in 0 until arr.size - 1) {
//            arr.get(index)
            val startTime: Int = pareTime(arr.get(index))
            list.add(LyricBean(startTime, content))
        }
        return list
    }

    //时间解析
    private fun pareTime(get: String): Int {
        //[00:00:31
        val timeString = get.substring(1)  //从下标1开始
        //
        val list = timeString.split(":")
        var hour = 0
        var min = 0
        var sec = 0f
        if (list.size == 3) {
            //小时
            hour = (list[0].toInt()) * 60 * 60 * 1000
            min = (list[1].toInt()) * 60 * 1000
            sec = (list[2].toFloat()) * 1000
        } else {
            min = (list[0].toInt()) * 60 * 1000
            sec = (list[1].toFloat()) * 1000
        }

        return (hour + min + sec).toInt()

    }
}