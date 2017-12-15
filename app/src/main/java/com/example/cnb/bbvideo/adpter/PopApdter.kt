package com.example.cnb.bbvideo.adpter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.cnb.bbvideo.widget.PopListItemView
import com.itheima.player.model.AudioBean

/**
 * Created by cnb on 2017/12/11.
 */
class PopApdter(var list: List<AudioBean>) : BaseAdapter() {

    //保存播放列表
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
          var itemView:PopListItemView? =null

            if (convertView==null) {
                itemView =PopListItemView(parent?.context)
            }else {
                itemView =position as PopListItemView

        }
        itemView.setData(list.get(position),position+1)

return itemView
    }

    override fun getItem(position: Int): Any {

        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return list.size
    }
}