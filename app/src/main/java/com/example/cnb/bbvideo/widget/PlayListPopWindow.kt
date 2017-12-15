package com.example.cnb.bbvideo.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.example.cnb.bbvideo.R
import org.jetbrains.anko.find


/**
 * Created by cnb on 2017/12/11.
 */
class PlayListPopWindow (context:Context,adapter:BaseAdapter,listener:AdapterView.OnItemClickListener,var window: Window): PopupWindow(){
        //记录当前应用程序的透明度
    var alpha :Float= 0f

    init {
alpha =window.attributes.alpha

        //设置布局
    val view =    LayoutInflater.from(context).inflate(R.layout.pop_playlist,null,false)

       val listView=view.find<ListView>(R.id.listView)
        //适配adpater
        listView.adapter=adapter
        //设置列表点击事件
        listView.setOnItemClickListener(listener)
        contentView=view




        //设置高度。宽度
        val manager=context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point=Point()
        manager.defaultDisplay.getSize(point)
        val  winH=point.y
        height =(winH*3)/5
        width=ViewGroup.LayoutParams.MATCH_PARENT
        //设置获取焦点
        isFocusable=true
        //设置外部点击
        isOutsideTouchable=true
        //能相应返回按钮（低版本popwindow 点击返回按钮）
        setBackgroundDrawable(ColorDrawable())
        //处理动画效果
        animationStyle=R.style.pop

    }
    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)

        val attribus=window.attributes
        attribus.alpha=0.3f

        //重新设置到应用程序的窗体上
        window.attributes=attribus
    }

    override fun dismiss() {
        super.dismiss()
        //恢复窗体透明度
        val attributes=window.attributes
        attributes.alpha=alpha
        window.attributes=attributes
    }

}

