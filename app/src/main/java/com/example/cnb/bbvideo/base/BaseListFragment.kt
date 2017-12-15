package com.example.cnb.bbvideo.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.cnb.bbvideo.R

import com.example.cnb.bbvideo.adpter.HomeAdapter
import com.example.cnb.bbvideo.presenter.impl.HomePresenterImpl
import com.example.cnb.bbvideo.view.HomeView

import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by cnb on 2017/12/5.
 *
 *  基类抽取
 * HomeView->BaseView
 * Presenter->BaseListPresenter
 * Adapter->BaseListAdapter
 *
 */
abstract class BaseListFragment<RESPONSE, ITEMBEAN, ITEMVIEW : View> : BaseFragment(), BaseView<RESPONSE> {


    //适配
    val adapter by lazy { getSpecialAdpater() }
    val presenter by lazy { getSpecialPresenter() }


    override fun onError(message: String?) {


    }

    override fun loadSuccess(response: RESPONSE?) {

        //刷新列表
        adapter.updataList(getList(response))
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
    }

    override fun loadMore(response: RESPONSE?) {


        adapter.loadMore(getList(response))
    }


    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initListener() {
        //初始化recycleView
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.DKGRAY)

        //刷新监听
        refreshLayout.isRefreshing = true

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
                    if ((layoutManager !is LinearLayoutManager)) return
                    val manager: LinearLayoutManager = layoutManager
                    val lastPosition = manager.findLastVisibleItemPosition()
                    if (lastPosition == adapter.itemCount - 1) {
                        presenter.loadMore(adapter.itemCount - 1)
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

    /**
     * 获取适配器Apdater
     */
    abstract fun getSpecialAdpater(): BaseListApdater<ITEMBEAN, ITEMVIEW>

    /**
     * 获取presenter
     */
    abstract fun getSpecialPresenter(): BaseListPresenter

    /**
     * 从返回结果中获取列表数据集合的方法
     */
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?

}