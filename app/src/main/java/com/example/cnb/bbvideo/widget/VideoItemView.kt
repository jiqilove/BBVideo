package com.example.cnb.bbvideo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R

import com.itheima.player.model.bean.VideosBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mv.view.*

/**
 * Created by cnb on 2017/12/5.
 */
class VideoItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_mv, this)
    }


    /**
     * 设置数据
     */
    fun setData(data: VideosBean) {


        //歌手名称
        author_name.text = data.artistName

        //歌曲名称
        aritst_title.text = data.title

        Picasso.with(context).load(data.playListPic).into(bg)

    }
}