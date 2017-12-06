package com.example.cnb.bbvideo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R


import com.itheima.player.model.bean.MusicBean
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_music.view.*


/**
 * 音乐界面每一个条目自定义的view
 */
class MusicItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_music, this)
    }

    fun setData(data: MusicBean.PlayListsBean) {
        //歌曲名称


        music_title.text = data.title
        //歌手名称
        name.text = data.creator?.nickName
        //条目
        count.text = data.videoCount.toString()

        // author_img.image=data.
        Picasso.with(context).load(data.playListBigPic).into(music_bg)
        //头像
        Picasso.with(context).load(data.creator?.largeAvatar).transform(CropCircleTransformation()).
                into(author_img)


    }

}