package com.example.cnb.bbvideo.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R
import com.itheima.player.model.AudioBean
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_vbang.view.*
import java.util.*

/**
 * Created by cnb on 2017/12/7.
 */
class VbangItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_vbang, this)
    }

    fun setData(itemBean: AudioBean) {

        //作者
        music_artist.text = itemBean.artist
        //歌名
        music_name.text = itemBean.display_name
        //大小
        music_size.text = Formatter.formatFileSize(context, itemBean.size)


    }

}