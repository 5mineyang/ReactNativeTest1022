package com.dragger2.reactnativetest1022.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.fragment.ViewPageFragment
import kotlinx.android.synthetic.main.activity_customview_practice.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/8
 */
class CustomViewPractice1 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample1_color, "drawColor()", R.layout.practice1_color))
        mViewPagerList.add(PageModel(R.layout.sample1_circle, "drawCircle()", R.layout.practice1_circle))
        mViewPagerList.add(PageModel(R.layout.sample1_rect, "drawRect()", R.layout.practice1_rect))
        mViewPagerList.add(PageModel(R.layout.sample1_point, "drawPoint()", R.layout.practice1_point))
        mViewPagerList.add(PageModel(R.layout.sample1_oval, "drawOval()", R.layout.practice1_oval))
        mViewPagerList.add(PageModel(R.layout.sample1_line, "drawLine()", R.layout.practice1_line))
        mViewPagerList.add(PageModel(R.layout.sample1_round_rect, "drawRoundRect()", R.layout.practice1_round_rect))
        mViewPagerList.add(PageModel(R.layout.sample1_arc, "drawArc()", R.layout.practice1_arc))
        mViewPagerList.add(PageModel(R.layout.sample1_path, "drawPath()", R.layout.practice1_path))
        mViewPagerList.add(PageModel(R.layout.sample1_histogram, "直方图", R.layout.practice1_histogram))
        mViewPagerList.add(PageModel(R.layout.sample1_pie_chart, "饼图", R.layout.practice1_pie_chart))
    }

    override fun initView() {
        super.initView()


        vpCustomViewPractice.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                val pageModel = mViewPagerList[position]
                return ViewPageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes)
            }

            override fun getCount(): Int {
                return mViewPagerList.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mViewPagerList[position].titleRes
            }
        }
        tlCustomViewPractice.setupWithViewPager(vpCustomViewPractice)

        vpCustomViewPractice.currentItem = 10
    }

    private inner class PageModel(var sampleLayoutRes: Int,var titleRes: String,var practiceLayoutRes: Int)
}
