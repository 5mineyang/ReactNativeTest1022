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
class CustomViewPractice7 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample7_argb_evaluator, R.string.title_argb_evaluator, R.layout.practice7_argb_evaluator))
        mViewPagerList.add(PageModel(R.layout.sample7_hsv_evaluator, R.string.title_hsv_evaluator, R.layout.practice7_hsv_evaluator))
        mViewPagerList.add(PageModel(R.layout.sample7_of_object, R.string.title_of_object, R.layout.practice7_of_object))
        mViewPagerList.add(PageModel(R.layout.sample7_property_values_holder, R.string.title_property_values_holder, R.layout.practice7_property_values_holder))
        mViewPagerList.add(PageModel(R.layout.sample7_animator_set, R.string.title_animator_set, R.layout.practice7_animator_set))
        mViewPagerList.add(PageModel(R.layout.sample7_keyframe, R.string.title_keyframe, R.layout.practice7_keyframe))
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

        vpCustomViewPractice.currentItem = 5
    }

    private inner class PageModel(var sampleLayoutRes: Int,var titleRes:Int,var practiceLayoutRes: Int)
}