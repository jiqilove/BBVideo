package com.example.cnb.bbvideo.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * Created by cnb on 2017/11/28.
 */
class SettingActivity:BaseActivity(), ToolBarManager{
    override fun getLayoutId(): Int {
        //preferenceFragment
        //PreferenceActivity
        return  R.layout.activity_setting
    }
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.layout_toolbar) }//初始化   惰性加载

    override fun initData() {
      settingToolBar()
        //获取推送通知有没有选中
        val sp=PreferenceManager.getDefaultSharedPreferences(this)
        sp.getBoolean("push",false)


    }


}