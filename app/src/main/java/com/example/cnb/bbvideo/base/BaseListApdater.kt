package com.example.cnb.bbvideo.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.cnb.bbvideo.adpter.HomeAdapter
import com.example.cnb.bbvideo.widget.HomeItemView
import com.example.cnb.bbvideo.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

/**
 * Created by cnb on 2017/12/5.
 * 所有下来刷新 和上啦加载更多的一个界面的基类
 *
 *
 */
abstract class BaseListApdater<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListApdater.BaseListHolder>() {
    override fun onBindViewHolder(holder: BaseListApdater.BaseListHolder?, position: Int) {
        //条目数据
        if (position == list.size) return
        val data = list.get(position)
        //条目view
        val itemView = holder?.itemView as ITEMVIEW

        //条目刷新
        refreshView(itemView, data)

        itemView.setOnClickListener {
            //条目点击事件
            listener?.let {
                it(data)
            }
//            listener?.invoke(data)   第二种fangshi
        }

    }

    var listener: ((itemBean: ITEMBEAN) -> Unit)? = null
    fun setMyListener(listener: (itemBean: ITEMBEAN) -> Unit) {

        this.listener = listener
    }


    private val list = ArrayList<ITEMBEAN>()
    /**
     * 刷新
     */
    fun updataList(list: List<ITEMBEAN>?) {
//        if(list==null)return   java  写法
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
            this.list.clear()
        }


    }

    /**
     * 加载更多
     */
    fun loadMore(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseListApdater.BaseListHolder {

        if (viewType == 1) {
            return BaseListHolder(LoadMoreView(parent?.context))
        } else
//            return BaseListHolder(HomeItemView(parent?.context))
            return BaseListHolder(getItemView(parent?.context))
    }


    override fun getItemViewType(position: Int): Int {

        if (position == list.size) {
            return 1
        } else {
            return 0
        }

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }


    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    abstract fun getItemView(context: Context?): ITEMVIEW

    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN)
}