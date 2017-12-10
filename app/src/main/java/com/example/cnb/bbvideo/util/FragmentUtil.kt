package com.example.cnb.bbvideo.util

import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.ui.fragment.*

/**
 * Created by cnb on 2017/11/28.
 */

//单例
class FragmentUtil private constructor() {  //私有构造方法

    val homeFragment by lazy { HomeFragment() }
    val videoFragment by lazy { VideoFragment() }
    val musicFragment by lazy { MusicFragment() }
    val presonFragment by lazy { PersonFragment() }
    val vbangFragment by lazy { VbangFragment() }

    companion object {
        val fragmentUtil= FragmentUtil()

    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId: Int): BaseFragment? {
        when (tabId) {
            R.id.tab_home -> return homeFragment
            R.id.tab_music -> return musicFragment
            R.id.tab_person -> return presonFragment
            R.id.tab_Vbang -> return vbangFragment
            R.id.tab_video -> return videoFragment

        }
        return null
    }


}