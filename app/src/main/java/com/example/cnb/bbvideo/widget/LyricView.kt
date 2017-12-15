package com.example.cnb.bbvideo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.util.LyricLoader
import com.example.cnb.bbvideo.util.LyricUtil
import com.itheima.player.model.LyricBean
import org.jetbrains.anko.doAsync
import java.time.Duration

/**
 * Created by cnb on 2017/12/11.
 */
class LyricView : View {
    var viewW: Int = 0
    var viewH: Int = 0
    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var lineHeight = 0
    var green = 0
    var duration = 0
    var progress = 0
    var centerLine = 0

    var updataProgress = true

    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    val list by lazy { ArrayList<LyricBean>() }


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        bigSize = resources.getDimension(R.dimen.bigszie)
        smallSize = resources.getDimension(R.dimen.smallsize)
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.green)
        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)
        //画笔
        paint.textAlign = Paint.Align.CENTER  //在x方向

        //循环添加歌词bean
//        for (i in 0 until 40) {
//            list.add(LyricBean(2000 * i, "正在播放地$i 行歌词"))
//        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        if (list.size <= 0) {
            //歌词没有加载
            drawSingleLine(canvas)

        } else {
            // 歌词已经加载
            drawMutieLine(canvas)
        }

    }

    /*
        //居中的寻找思路
        //当前时间大于当前行的开始时间，小于下一行的开始时间
        //判断剧中行是不是等于最后一行
        // 剧中行的行号
        */
    fun updataProgress(pro: Int) {
        if (!updataProgress) return
        if (list.size == 0) return

        this.progress = pro
        if (pro >= list.get(list.size - 1).startTime) {
            centerLine = list.size - 1
        } else {
            //其他行居中
            for (index in 0 until list.size - 1) {
                // progress>=当前行开始时间 progress 《下一行的开始时间
                val curStartTime = list.get(index).startTime
                val nextStartTime = list.get(index + 1).startTime

                if (pro > curStartTime && pro < nextStartTime) {
                    centerLine = index
//                    println("centerLine==$centerLine")
                    break
                }
            }
        }
        //找到剧中行
        /** 刷新的方法
        //        invalidate()  //onDraw()  他只会改变里面的内容，进行对空间里面条目的改变
        //        postInvalidate()// 在子线程中刷新布局
        //        requestLayout()  //view布局参数改变时刷新
         */
        invalidate()

    }

    var offsetY: Float = 0f
    //绘制多行居中文本
    private fun drawMutieLine(canvas: Canvas?) {

        if (updataProgress) {
//        歌词滚动的偏移量
            offsetY = lyricSliding()
        }
        val centerText = list.get(centerLine).content
        val bounds = Rect()
        paint.getTextBounds(centerText, 0, centerText.length, bounds)
        val textH = bounds.height()
        val centerY = viewH / 2 + textH / 2 - offsetY
        for ((index, value) in list.withIndex()) {
            if (index == centerLine) {
                paint.color = green
                paint.textSize = bigSize
            } else {
                paint.color = white
                paint.textSize = smallSize
            }
            val curX = viewW / 2
            val curY = centerY + (index - centerLine) * lineHeight

            //超出边界处理
            //处理超出边界的歌词
            if (curY < 0) continue
            //超出下面接
            if (curY > viewH + lineHeight) break
            val curText = list.get(index).content
            canvas?.drawText(curText, curX.toFloat(), curY.toFloat(), paint)
        }


    }

    //歌词滚动
    private fun lyricSliding(): Float {
        var lineTime = 0
        //随后一行
        if (centerLine == list.size - 1) {
            //行可用时间=duration -最后一行开始时间
            lineTime = duration - list.get(centerLine).startTime
        } else {
            //其他行剧中
            //            行可用时间=一下行开始见-剧中行开始时间
            val cententStartTime = list.get(centerLine).startTime
            val nextS = list.get(centerLine + 1).startTime
            lineTime = nextS - cententStartTime
        }
        //偏移开始建 =progress -居中行开始时间
        val offsetTime = progress - list.get(centerLine).startTime
        //偏移百分比 =便宜时间/行可用时间
        val offsetPercent = offsetTime / (lineTime.toFloat())
        //偏移y =便宜百分比*行高
        val offsetY = offsetPercent * lineHeight
        return offsetY
    }

    //设置当前歌曲播放的总时长
    fun setSongDuration(duration: Int) {
        this.duration = duration
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewH = h
        viewW = w
    }

    //设置歌曲播放名称
    //解析歌词文件添加到集合中
    fun setSongName(name: String) {

        doAsync {
            this@LyricView.list.clear()
            val file = LyricLoader.loaderLyricFile(name)
            println("aaa$file")

            val list = LyricUtil.pareLyric(file)
            this@LyricView.list.addAll(list)
        }
    }


    //歌词手势时间处理
    var downY = 0f
    var markY = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        /**
         * 1、手指按下时，停止歌词的更新
         */
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //手指按下时，停止更新歌词
                    updataProgress = false
                    downY = event.y
                    //用mark 来记录原来的已经更新了多少
                    markY = this.offsetY
                }

                MotionEvent.ACTION_UP -> {
                    //手指抬起
                    updataProgress = true
                }

                MotionEvent.ACTION_MOVE -> {
                    //手指移拖动
                    val endY = event.y
                    val offY = downY - endY
                    this.offsetY = offY + markY

                    //如果最终的偏移Y  大于行高  重新威威剧中
                    if (Math.abs(this.offsetY) >= lineHeight) {
                        //求居中行的行号便宜
                        val offsetLine = (this.offsetY / lineHeight).toInt()
                        centerLine += offsetLine
                        //d对剧中行的处理
                        if (centerLine < 0) centerLine = 0
                        else if (centerLine >= list.size - 1) {
                            centerLine = list.size - 1
                        }
                        this.downY = endY
                        //c重新确定偏移量
                        this.offsetY = this.offsetY % lineHeight
                        //重新记录y的偏移量
                        markY =this.offsetY

                        //跟新播放进度
//                        listener?.let {
//                            it(list.get(centerLine).startTime)
//                        }

                        listener?.invoke(list.get(centerLine).startTime)

                }


                    //重新绘制
                    invalidate()

                }
            }


        }

        return true
    }
    //进度函数回掉
var listener:((progress:Int)->Unit )?=null
    //设置
    fun setPrgressListener(listener: (progress:Int)->Unit){
this.listener=listener
    }

    //绘制单行剧中文本
    private fun drawSingleLine(canvas: Canvas?) {

        val text = "正在加载歌词"
        //初始化颜色和大小
        paint.textSize = bigSize
        paint.color = green

        val bounds = Rect()
//        paint.getTextBounds(text, 0, text.length, bounds)
        val textW = bounds.width()
        val textH = bounds.height()
        //        val x = - textW / 2
//        val y = viewH / 2 + textW / 2
//        canvas?.drawText(text, (viewW / 2).toFloat(), y.toFloat(), paint)
        canvas?.drawText(text, 0f, 0f, paint)
    }
}