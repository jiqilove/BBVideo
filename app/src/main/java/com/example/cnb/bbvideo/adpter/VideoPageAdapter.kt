package com.example.cnb.bbvideo.adpter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.cnb.bbvideo.ui.fragment.DefaultFragment

/**
 * Created by cnb on 2017/12/6.
 */
class VideoPageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
return  DefaultFragment()
    }

    override fun getCount(): Int {
      return 3
    }
}