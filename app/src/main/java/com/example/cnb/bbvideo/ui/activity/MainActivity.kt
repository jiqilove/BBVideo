package com.example.cnb.bbvideo.ui.activity


import android.support.v7.widget.Toolbar
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.util.FragmentUtil
import com.example.cnb.bbvideo.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.layout_toolbar) }//初始化   惰性加载


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        bottomBar.setOnTabSelectListener {

            val transaction =  supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,FragmentUtil.fragmentUtil.getFragment(it))
            transaction.commit()



        }

    }


}






