package com.example.cnb.bbvideo.adpter


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.cnb.bbvideo.widget.HomeItemView
import com.example.cnb.bbvideo.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

/**
 * Created by cnb on 2017/11/30.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolde>() {
    private val list =ArrayList<HomeItemBean>()
    /**
     * 刷新
     */
    fun updataList(list: List<HomeItemBean>?){
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
    fun loadMore(list: List<HomeItemBean>?){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolde {

        if(viewType==1){
            return HomeHolde(LoadMoreView(parent?.context))
        }else
            return HomeHolde(HomeItemView(parent?.context))
    }



    override fun onBindViewHolder(holder: HomeHolde?, position: Int) {
        //条目数据
        if(position==list.size) return
     val data =list.get(position)
        //条目view
        val itemView=holder?.itemView  as HomeItemView

        //条目刷新
        itemView.setData(data)

    }


    override fun getItemViewType(position: Int): Int {

        if(position==list.size){
            return 1
        }else {
            return 0
        }

    }

    override fun getItemCount(): Int {
        return  list.size+1
    }

    class HomeHolde(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}