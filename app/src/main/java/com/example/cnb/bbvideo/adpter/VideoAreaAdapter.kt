package com.example.cnb.bbvideo.adpter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.cnb.bbvideo.ui.fragment.VideoPageFragment
import com.itheima.player.model.bean.VideoAreaBean
import com.itheima.player.model.bean.VideosBean

/**
 * Created by cnb on 2017/12/5.
 */
class VideoAreaAdapter(val context:Context,val list: List<VideoAreaBean>?,fm: FragmentManager) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {

        //第一种数据传递
//        val fragment  =VideoPageFragment()
        val bundle =Bundle()
        bundle.putString("args",list?.get(position)?.code)
//        fragment.arguments=bundle
        //第二种数据传递

//    val fragment =Fragment.instantiate(context,(VideoPageFragment::class.java).toString(),bundle)
    val fragment =Fragment.instantiate(context,VideoPageFragment::class.java.name,bundle)


        return fragment
    }

    override fun getCount(): Int {
        return list?.size ?:0  //  如果不为空就返回 list
    }
override  fun  getPageTitle(position: Int) :CharSequence? {
    return  list?.get(position)?.name
}


}