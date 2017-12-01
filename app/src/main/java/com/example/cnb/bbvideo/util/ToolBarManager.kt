package com.example.cnb.bbvideo.util


import android.content.Intent
import  android.support.v7.widget.Toolbar;
import android.view.MenuItem
import android.widget.Toast

import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.ui.activity.SettingActivity


/**
 * Created by cnb on 2017/11/27.
 */
interface ToolBarManager {
    val toolbar: Toolbar
    /**
     * 初始化主界面toolbar
     */
    fun initMainToolBar() {
        toolbar.setTitle("勃勃影音")
        toolbar.inflateMenu(R.menu.main)

//第一种setOnMenuItemClickListener 写法

        toolbar.setOnMenuItemClickListener {
            toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
            true
        }

/*   第二种setOnMenuItemClickListener 写法
//        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.setting -> {
//                        //设置跳转界面
//                        //1、startActivity在这里行不通，因为没有activity环境
//                        toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
//                        //2、通过toolbar.context 来获取当前的activity，然后就可以使用startActivity
//                        //3、在这个 toolbar.context  == this.activity
//                    }
//                }
//return true
//            }
//        })
*/
    }

    fun settingToolBar() {
        toolbar.setTitle("设置")
    }

    fun aboutToolBar() {
        toolbar.setTitle("关于")
    }


}