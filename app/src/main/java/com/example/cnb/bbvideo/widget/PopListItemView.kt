package com.example.cnb.bbvideo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R
import com.itheima.player.model.AudioBean
import kotlinx.android.synthetic.main.item_pop.view.*
import kotlinx.android.synthetic.main.pop_playlist.view.*

/**
 * Created by cnb on 2017/12/11.
 */
class PopListItemView : RelativeLayout {
    var a: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_pop, this)
    }

    fun setData(data: AudioBean, position: Int) {
        music_title.text = data.display_name
        music_artist.text = data.artist
        cx.text =position.toString()

    }
}