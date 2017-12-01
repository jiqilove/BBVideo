package com.example.cnb.bbvideo.ui.activity

import android.support.v7.widget.Toolbar
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * Created by cnb on 2017/11/28.
 */
class AboutActivity : BaseActivity() ,ToolBarManager{

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.layout_toolbar) }//初始化   惰性加载

    override fun getLayoutId(): Int {
        return  R.layout.avtivity_about
    }

    override fun initData() {
        aboutToolBar()

    }



}