package com.example.cnb.bbvideo.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by cnb on 2017/11/27.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }
    /**
     * 出初始化数据
     */
  open  protected fun initData(){}
    /**
     * adpter
     * listener
     */
   open protected fun initListener() {    }

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int
    protected fun mTost(msg: String) {
        runOnUiThread { toast(msg) }
    }

    inline fun  <reified  T:BaseActivity>startActivityAndFinish(){
        startActivity<T>()
        finish()
    }


}