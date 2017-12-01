package com.example.cnb.bbvideo.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**
 * Created by cnb on 2017/11/27.
 */
class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        //进入主界面
//        startActivity<MainActivity>()
//        finish()

startActivityAndFinish<MainActivity>()//BaseActivity中已经吧两个步骤写好了，进入之后销毁页面

    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(splash_bg).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000)


    }

}
