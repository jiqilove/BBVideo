package com.example.cnb.bbvideo.widget


import android.content.Context

import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R
import com.itheima.player.model.bean.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by cnb on 2017/11/30.
 */
class  HomeItemView :RelativeLayout{
    //new 一个新的需要用到
    constructor(context: Context?) : super(context)
    //在清单文件布局中需要用到
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    //和主题相关
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

//    初始化的方法
    init {
        View.inflate(context, R.layout.item_home,this)
    }

    /**
     * 刷新条目数据
     */
    fun setData(data: HomeItemBean) {
        //歌曲名称设置
        title.setText(data.title)
        //简介
        desc.setText(data.description)
        //图片
        Picasso.with(context).load(data.posterPic).into(bg)
    }
}