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
class CustomViewPractice5 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample5_after_on_draw, R.string.title_after_on_draw, R.layout.practice5_after_on_draw))
        mViewPagerList.add(PageModel(R.layout.sample5_before_on_draw, R.string.title_before_on_draw, R.layout.practice5_before_on_draw))
        mViewPagerList.add(PageModel(R.layout.sample5_on_draw_layout, R.string.title_on_draw_layout, R.layout.practice5_on_draw_layout))
        mViewPagerList.add(PageModel(R.layout.sample5_dispatch_draw, R.string.title_dispatch_draw, R.layout.practice5_dispatch_draw))
        mViewPagerList.add(PageModel(R.layout.sample5_after_on_draw_foreground, R.string.title_after_on_draw_foreground, R.layout.practice5_after_on_draw_foreground))
        mViewPagerList.add(PageModel(R.layout.sample5_before_on_draw_foreground, R.string.title_before_on_draw_foreground, R.layout.practice5_before_on_draw_foreground))
        mViewPagerList.add(PageModel(R.layout.sample5_after_draw, R.string.title_after_draw, R.layout.practice5_after_draw))
        mViewPagerList.add(PageModel(R.layout.sample5_before_draw, R.string.title_before_draw, R.layout.practice5_before_draw))
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
                return getString(mViewPagerList[position].titleRes)
            }
        }
        tlCustomViewPractice.setupWithViewPager(vpCustomViewPractice)

        vpCustomViewPractice.currentItem = 7
    }

    private inner class PageModel(var sampleLayoutRes: Int,var titleRes:Int,var practiceLayoutRes: Int)
}