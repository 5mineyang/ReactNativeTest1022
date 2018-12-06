package com.dragger2.reactnativetest1022.activity

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.util.Log
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.fragment.NestedScrollViewFragment
import com.youke.yingba.my.adapter.MyFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_nestedscrollview.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/26
 */
class NestedScrollViewActivity : BaseActivity() {
    private val mTitles = arrayOf("11111", "22222", "33333", "44444", "55555", "66666")
    private var mFragmentList = ArrayList<Fragment>()
    private lateinit var mAdapter: MyFragmentPagerAdapter

    override fun bindLayout(): Int {
        return R.layout.activity_nestedscrollview
    }

    override fun initView() {
        super.initView()

        for (i in 0..5) {
            mFragmentList.add(NestedScrollViewFragment())
        }

        initAdapter()
    }

    override fun initListener() {
        super.initListener()

//        nsvNestedScrollView.setOnTouchListener { v, event ->
//            true
//        }

        //ScrollView滑动监听
        nsvNestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Log.d("NestedScrollActivity", "NestedScrollView：${v},scrollX：${scrollX},scrollY：${scrollY}，oldScrollX：${oldScrollX},oldScrollY：${oldScrollY}")
        }
    }

    private fun initAdapter() {
        mAdapter = MyFragmentPagerAdapter(supportFragmentManager, mFragmentList, mTitles)
        vpNestedScrollView.adapter = mAdapter
        //设置ViewPager缓存为6
        vpNestedScrollView.offscreenPageLimit = 6
        //将ViewPager和TabLayout绑定
        tlNestedScrollView.setupWithViewPager(vpNestedScrollView)
        //设置可以滑动
        tlNestedScrollView.tabMode = TabLayout.MODE_SCROLLABLE
    }
}