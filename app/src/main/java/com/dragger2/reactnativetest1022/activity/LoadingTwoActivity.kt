package com.dragger2.reactnativetest1022.activity

import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_loading_two.*


/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/5
 */
class LoadingTwoActivity : BaseActivity() {
    private var mPointPosition = Point()        //手指按下坐标
    companion object {
        const val IMAGE_SIZE = 300              //默认图片大小
    }

    override fun bindLayout(): Int {
        return R.layout.activity_loading_two
    }

    override fun initView() {
        super.initView()

        ivLoadingTwoActivity.setBackgroundResource(R.drawable.bookload)
        val animationDrawable = ivLoadingTwoActivity.background as AnimationDrawable
        animationDrawable.start()

        lineLoadingTwoActivity.setState(true)

        //初始设置一个layoutParams
        val layoutParams = RelativeLayout.LayoutParams(IMAGE_SIZE, IMAGE_SIZE)
        rlLoadingTwoActivityMove.layoutParams = layoutParams
    }

    override fun initListener() {
        super.initListener()

        //布局手势监听
        rlLoadingTwoActivityMove.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //将点下的点的坐标保存
                    mPointPosition.x = event.rawX.toInt()
                    mPointPosition.y = event.rawY.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    //计算出需要移动的距离
                    val dx = event.rawX.toInt() - mPointPosition.x
                    val dy = event.rawY.toInt() - mPointPosition.y
                    //将移动距离加上，现在本身距离边框的位置
                    val left = view.left + dx
                    val top = view.top + dy
                    //获取到layoutParams然后改变属性，在设置回去
                    val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
                    layoutParams.height = IMAGE_SIZE
                    layoutParams.width = IMAGE_SIZE
                    layoutParams.leftMargin = left
                    layoutParams.topMargin = top
                    view.layoutParams = layoutParams
                    //记录最后一次移动的位置
                    mPointPosition.x = event.rawX.toInt()
                    mPointPosition.y = event.rawY.toInt()
                }
                MotionEvent.ACTION_UP -> {
                    rlLoadingTwoActivityMove.setHorizontalGravity(Gravity.CENTER)
                    rlLoadingTwoActivityMove.setVerticalGravity(Gravity.CENTER)
                }
            }
            //刷新界面
            rlLoadingTwoActivity.invalidate()
            true
        }
    }
}