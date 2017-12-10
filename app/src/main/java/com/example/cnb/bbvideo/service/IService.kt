package com.example.cnb.bbvideo.service

import android.app.Service

/**
 * Created by cnb on 2017/12/8.
 */
interface  IService{
    fun updatePlayState()
    fun isPlaying():Boolean?
    fun getDuration(): Int
    fun getProgress(): Int
    fun seekTo(progress: Int)
    fun updatePlayMode()
    fun getPlayMode(): Int
    fun playPre()
    fun playNext()


}