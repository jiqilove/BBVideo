package com.example.cnb.bbvideo.util

import android.database.Cursor

/**
 * Created by cnb on 2017/12/6.
 *
 */
object  CursorUtil {
    fun  logCursor(cursor:Cursor?){
        cursor?.let {
            //将cursor游标复位
            it.moveToPosition(-1)
            while(it.moveToNext()){
                for (index in 0 until it.columnCount){
                    println("key=${it.getColumnName(index)} ---values=${it.getString(index)}")
                }
            }
        }

    }
}