package com.example.cnb.bbvideo.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.itheima.player.model.AudioBean
import de.greenrobot.event.EventBus
import org.jetbrains.anko.db.NULL
import java.util.*

/**
 * Created by cnb on 2017/12/8.
 */

class AudioService : Service() {
    var list: ArrayList<AudioBean>? = null
    var mediaPlayer: MediaPlayer? = null
    var position: Int = 0
    val binder by lazy { AudioBinder() }

    companion object {

        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }
    var mode = MODE_ALL
    override fun onCreate() {
        super.onCreate()
        //获取播放模式
        mode = sp.getInt("mode", 1)


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //获取集合
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        position = intent?.getIntExtra("position", -1) ?: -1
        //start play
        binder.playItem()
//
//        START_STICKY  粘性的service 强制杀死周 会长是重新启动service 不会传递原来的intent
//        START_NOT_STICKY 非粘性的是service 强制杀死之后  不会尝试重新启动sercvce
//        START_REDELIVER_INTENT  service 强制杀死之后  会长是重新启动service 会传递原来的intent
        return START_NOT_STICKY
    }


    override fun onBind(intent: Intent?): IBinder {
        return binder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        //上一曲
        override fun playPre() {
            //获取播放歌曲的position
            list?.let {
                when (mode) {
                    MODE_RANDOM -> list?.let {
                        position = Random().nextInt(it.size - 1)
                    }
                    else -> {
                        if (position == 0) {
                            position = it.size - 1
                        } else {
                            position--
                        }
                    }
                }
            }
            playItem()

        }

        //下一曲
        override fun playNext() {
            list?.let {
                when (mode) {
                    MODE_RANDOM ->position = Random().nextInt(it.size - 1)

                    else -> position =(position+1)%it.size
                }
            }
            playItem()



        }

        override fun getPlayMode(): Int {
            return mode
        }

        //修改模式
        override fun updatePlayMode() {
//            val MODE_ALL = 1           val MODE_SINGLE = 2         val MODE_RANDOM = 3
            when (mode) {
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL

            }
            //播放模式的保存
            sp.edit().putInt("mode", mode).commit()

        }

        //歌曲播放完成
        override fun onCompletion(mp: MediaPlayer?) {
            //
            autoPlayNext()


        }

        //跳转到当前进度去播放
        override fun seekTo(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        //获取当前进度
        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }

        //获取中进度
        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0
        }


        override fun updatePlayState() {
            //

            val isPlaying = isPlaying()
            isPlaying?.let {
                if (isPlaying) {
                    //播放  -》暂停
                    mediaPlayer?.pause()

                } else {
                    //暂定  -》播放
                    mediaPlayer?.start()
                }
            }
        }

        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }


        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            //通知界面根棍
            notifyUpdateUi()


        }

        fun playItem() {

            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }


        }


        //自动播放下一曲
        private fun autoPlayNext() {

            when (mode) {
                MODE_ALL -> {
                    list?.let {
                        position = (position + 1) % it.size  //最后一条
                    }
                }
                MODE_RANDOM ->
                    list?.let {
                        position = Random().nextInt(it.size)
                    }
            }

            playItem()
        }


    }


    /**
     * 通知界面的更新
     */
    private fun notifyUpdateUi() {

        //发送端
        EventBus.getDefault().post(list?.get(position))
    }
}