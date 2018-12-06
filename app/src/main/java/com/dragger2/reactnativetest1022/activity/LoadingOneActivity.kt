package com.dragger2.reactnativetest1022.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.view.View
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import kotlinx.android.synthetic.main.activity_loading_one.*
import kotlinx.coroutines.experimental.async

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/5
 */
class LoadingOneActivity : BaseActivity() {
    private var mIsPlaying = true       //子线程是否开启
    private var i = -1
    private val mHandler = Handler()

    override fun bindLayout(): Int {
        return R.layout.activity_loading_one
    }

    override fun initView() {
        super.initView()

        //开启线程分别显示图片
        async {
            while (mIsPlaying) {
                Thread.sleep(100)
                runOnUiThread {
                    i++
                    setImageShow(i)
                    if (i == 7) {
                        i = -1
                    }
                }
            }
        }

        //设置动画
        setAnim()
    }

    override fun initListener() {
        super.initListener()

        //按钮点击
        btnLodingOneActivityClick.setOnClickListener {
            //添加动画
            plLodingOneActivity.addHeart()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mIsPlaying = false
    }

    //设置图片方法
    private fun setImageShow(i: Int) {
        //所有viewId
        val mAllView = arrayOf(ivLoadingActivityOne, ivLoadingActivityTwo, ivLoadingActivityThree, ivLoadingActivityFour, ivLoadingActivityFive, ivLoadingActivitySix, ivLoadingActivitySeven, ivLoadingActivityEight)
        mAllView.forEach {
            it.visibility = View.GONE
        }
        mAllView[i].visibility = View.VISIBLE
    }

    //设置动画
    private fun setAnim() {
        val animator1 = ObjectAnimator.ofFloat(dw1CustomView, "degreeY", -45f)
        animator1.duration = 500
        animator1.startDelay = 500
        val animator2 = ObjectAnimator.ofFloat(dw1CustomView, "degreeZ", 0f, 270f)
        animator2.duration = 500
        animator2.startDelay = 500
        val animator3 = ObjectAnimator.ofFloat(dw1CustomView, "fixDegreeY", 0f, 30f)
        animator3.duration = 500
        animator3.startDelay = 500

        val animatorSet = AnimatorSet()
        animatorSet.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                //延迟0.5秒
                mHandler.postDelayed({
                    runOnUiThread{
                        //在结束时再次重复动画
                        dw1CustomView.reset()
                        animatorSet.start()
                    }
                },500)
            }
        })
        animatorSet.playSequentially(animator1,animator2)
        animatorSet.start()
    }
}