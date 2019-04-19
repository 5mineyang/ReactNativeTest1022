package com.dragger2.reactnativetest1022.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.adapter.BannerViewPagerAdapter
import com.dragger2.reactnativetest1022.bean.BannerBean
import kotlinx.android.synthetic.main.fragment_maintablayout_one.*
import java.util.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/26
 */
class MainTabLayoutFragmentOne : Fragment() {
    private val UPTATE_VIEWPAGER = 0
    private lateinit var list: List<BannerBean>
    private lateinit var mPagerAdapter: BannerViewPagerAdapter
    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask
    private var autoCurrIndex = 0   //设置当前 第几个图片 被选中
    private var period = 5000L      //轮播图展示时长,默认5秒
    //用Hundler去更新ui
    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UPTATE_VIEWPAGER -> {
                    if (msg.arg1 != 0) {
                        vpMainTableLayoutViewPagerContent.currentItem = msg.arg1
                    } else {
                        //false 当从末页调到首页时，不显示翻页动画效果，
                        vpMainTableLayoutViewPagerContent.setCurrentItem(msg.arg1, false)
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maintablayout_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
        initListener()
    }

    private fun initData() {
        //初始化list数据
        list = ArrayList()
        for (i in 0..3) {
            val listBean = BannerBean()
            if (i == 0) {
                listBean.bannerName = "动画片"
                listBean.bannerUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
                listBean.playTime = 10000
                //图片类型 视频
                listBean.urlType = 1
                (list as ArrayList<BannerBean>).add(listBean)
            } else {
                listBean.bannerName = "广告"
                listBean.bannerUrl = "http://pic11.nipic.com/20101201/4452735_182232064453_2.jpg"
                listBean.playTime = 3000
                //图片类型 图片
                listBean.urlType = 0
                (list as ArrayList<BannerBean>).add(listBean)
            }
        }
        period = list[0].playTime.toLong()
    }

    private fun initView() {
        //设置预加载数量
        vpMainTableLayoutViewPagerContent.offscreenPageLimit = 0

        initAdapter()

        createTimerTask()//创建定时器
        timer = Timer()
        timer.schedule(timerTask, 5000, period)
    }

    //监听事件
    fun initListener() {
        vpMainTableLayoutViewPagerContent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                autoCurrIndex = position//动态设定轮播图每一页的停留时间
                period = list[position].playTime.toLong()
                if (timer != null) {//每次改变都需要重新创建定时器
                    timer.cancel()
//                    timer = null
                    timer = Timer()
                    if (timerTask != null) {
                        timerTask.cancel()
//                        timerTask = null
                        createTimerTask()
                    }
                    timer.schedule(timerTask, period, period)
                }
            }
        })
    }

    //适配器
    private fun initAdapter() {
        mPagerAdapter = BannerViewPagerAdapter(context as Activity?, list)
        vpMainTableLayoutViewPagerContent.adapter = mPagerAdapter
    }

    private fun createTimerTask() {
        timerTask = object : TimerTask(){
            override fun run() {
                val message = Message()
                message.what = UPTATE_VIEWPAGER
                if (autoCurrIndex == list.size - 1) {
                    autoCurrIndex = -1
                }
                message.arg1 = autoCurrIndex + 1
                mHandler.sendMessage(message)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (timerTask != null) {
            timerTask.cancel()
//            timerTask = null
        }
        if (timer != null) {
            timer.cancel()
//            timer = null
        }
    }
}