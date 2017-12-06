package com.example.cnb.bbvideo.ui.activity



import android.support.v4.view.ViewPager
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.model.VideoPlayBean

import cn.jzvd.JZVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*
import cn.jzvd.JZVideoPlayer
import com.example.cnb.bbvideo.adpter.VideoPageAdapter




/**
 * ClassName:VideoPlayerActivity
 * Description:
 */
class JiecaoVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao

    }

    override fun initData() {
        //获取传递的数据
        val data = intent.data
        if (data == null) {
            //从应用内
            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
            jcvideoplayer.setUp(videoPlayBean.url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoPlayBean.title)
        } else {
            //应用外
            if (data.toString().startsWith("http")) {
                jcvideoplayer.setUp(data.toString(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, data.toString())
            } else {
                jcvideoplayer.setUp(data.path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, data.toString())
            }

        }


//        val jzVideoPlayerStandard = findViewById<View>(R.id.jcvideoplayer) as JZVideoPlayerStandard
//        jcvideoplayer.setUp("url", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "title")


    }

    //生命周期绑定
    override fun onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }

    override fun initListener() {

        videoViewPage.adapter = VideoPageAdapter(supportFragmentManager)
        rg.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb1->videoViewPage.setCurrentItem(1)
                R.id.rb2->videoViewPage.setCurrentItem(2)
                R.id.rb3->videoViewPage.setCurrentItem(3)

            }

        }
        videoViewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            /**
             *
             */
            override fun onPageScrollStateChanged(state: Int) {

            }

            /**
             * 滑动回调
             */
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            /**
             * 选中状态改变
             */
            override fun onPageSelected(position: Int) {

                when(position)
                {
                    0->rg.check(R.id.rb1)
                    1->rg.check(R.id.rb2)
                    2->rg.check(R.id.rb3)
                }

            }

        })

        

    }



}