package com.dragger2.reactnativetest1022.activity

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_surface.*
import java.util.*

/**
 *  Description : SurfaceView练习

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2019/4/26
 */
class SurfaceActivity : BaseActivity() {
    private lateinit var mSurfaceHolder: SurfaceHolder
    private var mTimer = Timer()
    private var mMyTimerTask: MyTimerTask? = null
    private lateinit var Y_axis: Array<Int?>
    private var centerY = 0    //中心轴位置
    private var oldX = 0
    private var oldY = 0
    private var currentX = 0

    override fun bindLayout(): Int {
        return R.layout.activity_surface
    }

    override fun initView() {
        super.initView()

        mSurfaceHolder = sfvSurface.holder
        //找到view中心轴
        centerY = (windowManager.defaultDisplay.height - sfvSurface.top) / 2
        Y_axis = arrayOfNulls(windowManager.defaultDisplay.width)
        //计算正弦波
        for (i in 0 until Y_axis.size) {
            Y_axis[i] = centerY - (100 * Math.sin((i + 1) * 2 * Math.PI / 180)).toInt()
        }
    }

    override fun initListener() {
        super.initListener()

        btnSurfaceStartOne.setOnClickListener {
            mMyTimerTask?.let {
                it.cancel()
                clearDraw()
                currentX = 0
            }
            mMyTimerTask = MyTimerTask()
            oldY = centerY
            mTimer.schedule(mMyTimerTask, 0, 5)
        }

        btnSurfaceStartTwo.setOnClickListener {
            simpleDraw(Y_axis.size - 1)
        }
    }

    private fun simpleDraw(length: Int) {
        if (length == 0) {
            oldX = 0
        }
        val canvas = mSurfaceHolder.lockCanvas(Rect(oldX, 0, oldX + length, windowManager.defaultDisplay.height))
        val paint = Paint()
        paint.color = Color.GREEN
        paint.strokeWidth = 2f
        var y: Int
        for (i in oldX + 1 until length) {
            y = Y_axis[i - 1]!!
            canvas.drawLine(oldX.toFloat(), oldY.toFloat(), i.toFloat(), y.toFloat(), paint)
            oldX = i
            oldY = y
        }
        mSurfaceHolder.unlockCanvasAndPost(canvas)
    }

    private fun clearDraw() {
        val canvas = mSurfaceHolder.lockCanvas(null)
        canvas.drawColor(Color.BLACK)
        mSurfaceHolder.unlockCanvasAndPost(canvas)
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            simpleDraw(currentX)
            //往后移
            currentX++
            if (currentX == Y_axis.size - 1) {
                clearDraw()
                currentX = 0
                oldY = centerY
            }
        }
    }
}