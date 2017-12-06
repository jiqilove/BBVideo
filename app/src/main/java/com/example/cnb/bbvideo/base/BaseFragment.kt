package com.example.cnb.bbvideo.base


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * Created by cnb on 2017/11/27.
 */

abstract class  BaseFragment : Fragment() ,AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    /**
     * fragment 初始化数据
     */
 open   protected fun init() {

    }

    abstract  fun getLayoutId():Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context,getLayoutId(),null)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()

    }

    /**
     * 数据初始化
     */
    abstract fun initData()



    abstract  fun initListener()




     fun mTost(msg: String) {
         context.runOnUiThread { mTost(msg) }
    }

}
