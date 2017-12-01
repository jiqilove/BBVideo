package com.example.cnb.bbvideo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.cnb.bbvideo.R

/**
 * Created by cnb on 2017/11/30.
 */
class LoadMoreView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
init {
   View.inflate(context, R.layout.view_loadmore,this)
}




}