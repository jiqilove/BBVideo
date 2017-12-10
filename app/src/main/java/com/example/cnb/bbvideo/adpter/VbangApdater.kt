package com.example.cnb.bbvideo.adpter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import com.example.cnb.bbvideo.widget.VbangItemView
import com.itheima.player.model.AudioBean

/**
 * Created by cnb on 2017/12/7.
 *  V
 */
class VbangApdater(context: Context?, cursor: Cursor?) : CursorAdapter(context, cursor) {
    /*
    创建条目
     */
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VbangItemView(context)

    }

    /**
     * 帮定
     */
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        //view
        val itemView = view as VbangItemView
        //data
        val  itemBean =AudioBean.getAudioBean(cursor)

        itemView.setData(itemBean)
    }
}