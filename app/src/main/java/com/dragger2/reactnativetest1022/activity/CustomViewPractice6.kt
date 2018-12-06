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
class CustomViewPractice6 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample6_translation, R.string.title_translation, R.layout.practice6_translation))
        mViewPagerList.add(PageModel(R.layout.sample6_rotation, R.string.title_rotation, R.layout.practice6_rotation))
        mViewPagerList.add(PageModel(R.layout.sample6_scale, R.string.title_scale, R.layout.practice6_scale))
        mViewPagerList.add(PageModel(R.layout.sample6_alpha, R.string.title_alpha, R.layout.practice6_alpha))
        mViewPagerList.add(PageModel(R.layout.sample6_multi_properties, R.string.title_multi_properties, R.layout.practice6_multi_properties))
        mViewPagerList.add(PageModel(R.layout.sample6_duration, R.string.title_duration, R.layout.practice6_duration))
        mViewPagerList.add(PageModel(R.layout.sample6_interpolator, R.string.title_interpolator, R.layout.practice6_interpolator))
        mViewPagerList.add(PageModel(R.layout.sample6_object_anomator, R.string.title_object_animator, R.layout.practice6_object_animator))
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