package com.example.cnb.bbvideo.ui.fragment


import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.adpter.HomeAdapter
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.presenter.impl.HomePresenterImpl
import com.example.cnb.bbvideo.util.ThreadUtil
import com.example.cnb.bbvideo.util.URLProviderUtils
import com.example.cnb.bbvideo.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.jetbrains.anko.support.v4.toast
import java.io.IOException

/**
 * Created by cnb on 2017/11/28.
 */
class HomeFragment : BaseFragment(), HomeView {
    override fun onError(message: String?) {
        toast("加载数据失败")
    }

    override fun loadSuccess(list: List<HomeItemBean>?) {
        //隐藏控件
        refreshLayout.isRefreshing = false
        //更新列表
        adpter.updataList(list)


    }

    override fun loadMore(list: List<HomeItemBean>?) {
        adpter.loadMore(list)
    }

    //适配
    val adpter by lazy { HomeAdapter() }
    val presenter by lazy { HomePresenterImpl(this) }
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initListener() {
//初始化recycleView
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adpter
        //初始化刷新控件

        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.DKGRAY)
        //刷新监听
        refreshLayout.isRefreshing=true
        refreshLayout.setOnRefreshListener {
            //刷新监听监听数据
            presenter.loadDatas()
        }

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
/*
//                开始滚动    SCROLL_STATE_FLING
//                正在滚动    SCROLL_STATE_TOUCH_SCROLL
//                已经停止    SCROLL_STATE_IDLE
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_IDLE -> {
//                        println("停止滚动")
//                    }
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {
//                        println("开始拖动")
//                    }
//                    RecyclerView.SCROLL_STATE_SETTLING -> {
//                        println("stting")
//                    }
//                }
*/
                val layoutManager = recyclerView?.layoutManager
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //最后一条是否显示

                    if (layoutManager is LinearLayoutManager) {
                        val manager: LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adpter.itemCount-1 ) {
                            presenter.loadMore(adpter.itemCount - 1)
                        }
                    }
                }


            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                println("onScrolled dx=$dx  dy=$dy")

            }
        })

    }

    override fun initData() {
        presenter.loadDatas()
    }


}