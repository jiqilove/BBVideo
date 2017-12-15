package com.example.cnb.bbvideo.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.R.layout.activity_musicplaye_button
import com.example.cnb.bbvideo.adpter.PopApdter
import com.example.cnb.bbvideo.base.BaseActivity
import com.example.cnb.bbvideo.service.AudioService
import com.example.cnb.bbvideo.service.IService
import com.example.cnb.bbvideo.util.StringUtil
import com.example.cnb.bbvideo.widget.PlayListPopWindow
import com.itheima.player.model.AudioBean
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_musicplaye_button.*
import kotlinx.android.synthetic.main.activity_musicplaye_centerr.*
import kotlinx.android.synthetic.main.activity_musicplaye_top.*


/**
 * Created by cnb on 2017/12/7.
 */
class AudioPlayerActivity : BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener {
    //弹出列表的点击事件
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//播放当前的歌曲
        val playPosition = iService?.playPosition(position)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_musicplayer

    }


    /**
     *  进度改变回调
     *  progress 改变之后的进度
     *  fromUser 是否事通过拖动的形式来改变进度的  ，false 代表通过代码的方式改变进度
     *
     */
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //判断时候通过拖动的形式
        if (!fromUser) return
        //跟新播放进度
        iService?.seekTo(progress)
        //更新进度显示
        updateProgress(progress)
    }

    /**
     * 手指触摸seekbar
     */
    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    /**
     * 手指离开seekbar
     */
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    var audioBean: AudioBean? = null
    var drawable: AnimationDrawable? = null
    var duration: Int = 0


    var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_PROGRESS -> startUpdateProgress()
            }
        }


    }


    val MSG_PROGRESS = 0


    //更新播放模式
    private fun updatePlayMode() {
        //修改service中的模式
        iService?.updatePlayMode()
        //修改界面模式的图标
        updatePlayModeBtn()
    }

    //根据当前模式来修改当前的图标
    private fun updatePlayModeBtn() {
        //获取模式
        iService?.let {
            val mMode: Int = it.getPlayMode()
            when (mMode) {
                AudioService.MODE_ALL -> play_mode.setBackgroundResource(R.mipmap.btn_playmode_all_repeat_normal)
                AudioService.MODE_SINGLE -> play_mode.setBackgroundResource(R.mipmap.btn_playmode_singlerepeat_normal)
                AudioService.MODE_RANDOM -> play_mode.setBackgroundResource(R.mipmap.btn_playmode_random_normal)
            }
        }

        //设置图标
    }

    /**
     * 接受eventbus方法
     */
    fun onEventMainThread(itemBean: AudioBean) {
        //设置播放歌曲
        geci.setSongName(itemBean.display_name)
        println("6666${itemBean.display_name}")

        //歌手歌曲的名称
        this.audioBean = itemBean
        //设置歌手名称
        music_play_name.text = itemBean.artist
        //设置歌曲名称
        music_play_title.text = itemBean.display_name
        //按钮状态更新
        updatePlayStateBtn()
        //动画播放
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //获取时间总进度
        duration = iService?.getDuration() ?: 0
        geci.setSongDuration(duration)


        //进度条   设置最大值
        progress_sk.max = duration
        //更新播放进度
        startUpdateProgress()
        //跟新播放模式图标
        updatePlayModeBtn()
    }

    private fun startUpdateProgress() {
        //h获取当前进度
        val progress: Int = iService?.getProgress() ?: 0
        //更新当前进度的数据
        updateProgress(progress)
        //定时获取进度
        handler.sendEmptyMessage(MSG_PROGRESS)

    }

    //根据当前进度更新数据 来跟新界面
    private fun updateProgress(pro: Int) {
        // 更新进度的数据
        val progress = StringUtil.parseDuratoin(pro) + "/" + StringUtil.parseDuratoin(duration)
        //进度条时间
        bottom_progress.text = progress
        //进度条
        progress_sk.setProgress(pro)
        //更新歌词播放进度
        geci.updataProgress(pro)

    }


    val conn by lazy { AudioConnetion() }


    private fun updatePlayState() {
        //更新播放状态
        iService?.updatePlayState()
        //更新播放iconic 图标状态
        updatePlayStateBtn()

    }

    private fun updatePlayStateBtn() {
        //获取当前状态
        val isPlaying = iService?.isPlaying()

        isPlaying?.let {
            //根据状态来跟新图标
            if (isPlaying) {
                //播放
                state.setBackgroundResource(R.mipmap.btn_audio_pause_normal)
                drawable?.start()//动画的播放
                //开启进度更新
                handler.sendEmptyMessage(MSG_PROGRESS)

            } else {
                state.setBackgroundResource(R.mipmap.btn_audio_play_normal)
                drawable?.stop()
                //移除进度更新
                handler.removeMessages(MSG_PROGRESS)
            }
        }
    }


    override fun initData() {
        EventBus.getDefault().register(this)
/*
//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position", -1)
//        通过audioservice 播放音乐
//        val intent = Intent(this, AudioService::class.java)
//        通过intent 将list 以及position 传递过去
//        intent.putExtra("list", list)
//        intent.putExtra("position", position)

*/

        val intent = intent
        //修改
        intent.setClass(this, AudioService::class.java)

        //先绑定
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        //在开启
        startService(intent)

/*
//        //播放音乐
//        val mediaPlayer = MediaPlayer()//
//        mediaPlayer.setOnPreparedListener {
//            mediaPlayer.start()
//        }
//
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()
*/
    }

    var iService: IService? = null

    inner class AudioConnetion : ServiceConnection {
        //这个方法是意外断开连接
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        //service 连接时
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(conn)
        //反注册
        EventBus.getDefault().unregister(this)

        //清楚handle 发送的所有消息
        handler.removeCallbacksAndMessages(null)
    }


    override fun initListener() {
        // 播放状态设置
        state.setOnClickListener(this)
        // 返回键
        back.setOnClickListener { finish() }
        //进度条
        progress_sk.setOnSeekBarChangeListener(this)
        //模式切换
        play_mode.setOnClickListener(this)
        //上一曲
        pre.setOnClickListener(this)
        //下一曲
        next.setOnClickListener(this)
        //播放列表
        music_list.setOnClickListener(this)
        //歌词拖动进度的更新
geci.setPrgressListener {

    //跟新播放进度
    iService?.seekTo(it)

    updateProgress(it)
}

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.state -> updatePlayState()
            R.id.play_mode -> updatePlayMode()
            R.id.pre -> iService?.playPre()
            R.id.next -> iService?.playNext()
            R.id.music_list -> showPlayList()
        }


    }

    // 显示列表
    private fun showPlayList() {
        val list = iService?.getPlayList()
        list?.let {
            val adapter = PopApdter(list)
            val H = player_bottom.height
            val popWindow = PlayListPopWindow(this, adapter, this, window)
            popWindow.showAsDropDown(player_bottom, 0, -H)
        }

    }

}