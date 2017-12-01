package com.example.cnb.bbvideo.util

import android.os.Handler
import android.os.Looper


/**
 * Created by cnb on 2017/11/30.
 */
object ThreadUtil {
    val handle = Handler(Looper.getMainLooper())

   fun runOnMainThread(runnable: Runnable) {
        handle.post(runnable)
    }
}