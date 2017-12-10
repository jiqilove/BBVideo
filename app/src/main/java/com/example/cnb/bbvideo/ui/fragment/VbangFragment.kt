package com.example.cnb.bbvideo.ui.fragment

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.adpter.VbangApdater
import com.example.cnb.bbvideo.base.BaseFragment
import com.example.cnb.bbvideo.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import android.Manifest
import android.content.pm.PackageManager
import com.example.cnb.bbvideo.ui.activity.AudioPlayerActivity
import com.itheima.player.model.AudioBean
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton
import javax.crypto.Cipher


/**
 * Created by cnb on 2017/12/6.
 */
class VbangFragment : BaseFragment() {
//    val handler = object : Handler() {
//        override fun handleMessage(msg: Message?) {
//            msg.let {
//                val cursor: Cursor = msg?.obj as Cursor
//            }
//
//        }
//    }

    override fun getLayoutId(): Int = R.layout.fragment_vbang
    //    val adapter by  lazy { VbangApdater(context,) }
    var adapter: VbangApdater? = null

    override fun initListener() {

        adapter = VbangApdater(context, null)
        listView.adapter = adapter

        listView.setOnItemClickListener {adapterView,View,i,l->
            //获取数据集合
         val cursor=   adapter?.getItem(i) as  Cursor
            //通过当前位置来获取播放列表
          val list: ArrayList<AudioBean> =  AudioBean.getAudioBeans(cursor)


            //位置position
            startActivity<AudioPlayerActivity>("list" to list ,"position" to i)

        }





    }

    override fun initData() {
        //longSong()
        //动态权限申请
        handlerPermission()


    }

    private fun handlerPermission() {
        /**
         * 1、检查时候是否权限  checkpermission
         * 2、是否要像是自定以提示
         * 3、请求权限
          */
        // 1、检查时候是否权限  checkpermission
        val permission =Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission=    ActivityCompat.checkSelfPermission(context,permission)
        //2、是否要像是自定以提示
        if (checkSelfPermission ==PackageManager.PERMISSION_GRANTED){
            longSong()
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)){
                //需要弹出
                alert ("即将访问你的音乐文件","温馨提示" ){
                    yesButton { myRequestPermission() }//3、请求权限
                noButton {  }
                }.show()

            }else{
                //不需要弹出
           myRequestPermission()
            }
        }


    }

    private fun myRequestPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions,1)

    }

    /**
     * 接受权限授权结果
     * requestCode 请求吗
     * permission 申请数组
     * grantResults 申请之后的结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            longSong()
        }


    }



    /**
     * 音乐查询的异步查询
     */


    override fun onDestroy() {
        super.onDestroy()
        //界面销毁的时候关闭 cursor

        //获取addapter 中的cuorsor


        //将adapter中的cursor设置为null
        adapter?.changeCursor(null)
    }


    private fun longSong() {
        /*//            val cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                    arrayOf(MediaStore.Audio.Media.DATA,
//                            MediaStore.Audio.Media.SIZE,
//                            MediaStore.Audio.Media.DISPLAY_NAME,
//                            MediaStore.Video.Media.ARTIST), null, null, null)
//
//            CursorUtil.logCursor(cursor)/
*/
        //开启线程加载音乐数据
        /*
        //        Thread(object : Runnable {
        //            override fun run() {
        //                val cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        //                        arrayOf(MediaStore.Audio.Media.DATA,
        //                                MediaStore.Audio.Media.SIZE,
        //                                MediaStore.Audio.Media.DISPLAY_NAME,
        //                                MediaStore.Video.Media.ARTIST), null, null, null)
        //
        //                val msg = Message.obtain()
        //                msg.obj = cursor
        //                handler.sendMessage(msg)
        //            }
        //        }).start()
        */
        //asynctask

        //        AudioTask().execute(resolver)


        val resolver = context.contentResolver

//        longSong()


        val handler = object : AsyncQueryHandler(resolver) {

            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //查询完成回调 主线程中
                //打印回调

//                CursorUtil.logCursor(cursor)
//                (cookie as VbangApdater).notifyDataSetInvalidated()

                //设置数据源
                //刷新adpater
                (cookie as VbangApdater).swapCursor(cursor)

            }
        }


        //开始查询
        handler.startQuery(0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.ARTIST), null, null, null)

    }

    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        /**
         * 后台执行任务 新线程
         */
        override fun doInBackground(vararg params: ContentResolver?): Cursor? {
            val cursor = params[0]?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.Audio.Media.DATA,
                            MediaStore.Audio.Media.SIZE,
                            MediaStore.Audio.Media.DISPLAY_NAME,
                            MediaStore.Video.Media.ARTIST), null, null, null)
            return cursor
        }

        /**
         * 将后台任务结果回到主线程
         */
        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            //
            CursorUtil.logCursor(result)
        }

    }
}