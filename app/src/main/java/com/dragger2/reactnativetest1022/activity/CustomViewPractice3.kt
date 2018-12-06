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
class CustomViewPractice3 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample3_draw_text, R.string.title_draw_text, R.layout.practice3_draw_text))
        mViewPagerList.add(PageModel(R.layout.sample3_static_layout, R.string.title_static_layout, R.layout.practice3_static_layout))
        mViewPagerList.add(PageModel(R.layout.sample3_set_text_size, R.string.title_set_text_size, R.layout.practice3_set_text_size))
        mViewPagerList.add(PageModel(R.layout.sample3_set_typeface, R.string.title_set_typeface, R.layout.practice3_set_typeface))
        mViewPagerList.add(PageModel(R.layout.sample3_set_fake_bold_text, R.string.title_set_fake_bold_text, R.layout.practice3_set_fake_bold_text))
        mViewPagerList.add(PageModel(R.layout.sample3_set_strike_thru_text, R.string.title_set_strike_thru_text, R.layout.practice3_set_strike_thru_text))
        mViewPagerList.add(PageModel(R.layout.sample3_set_underline_text, R.string.title_set_underline_text, R.layout.practice3_set_underline_text))
        mViewPagerList.add(PageModel(R.layout.sample3_set_text_skew_x, R.string.title_set_text_skew_x, R.layout.practice3_set_text_skew_x))
        mViewPagerList.add(PageModel(R.layout.sample3_set_text_scale_x, R.string.title_set_text_scale_x, R.layout.practice3_set_text_scale_x))
        mViewPagerList.add(PageModel(R.layout.sample3_set_text_align, R.string.title_set_text_align, R.layout.practice3_set_text_align))
        mViewPagerList.add(PageModel(R.layout.sample3_get_font_spacing, R.string.title_get_font_spacing, R.layout.practice3_get_font_spacing))
        mViewPagerList.add(PageModel(R.layout.sample3_measure_text, R.string.title_measure_text, R.layout.practice3_measure_text))
        mViewPagerList.add(PageModel(R.layout.sample3_get_text_bounds, R.string.title_get_text_bounds, R.layout.practice3_get_text_bounds))
        mViewPagerList.add(PageModel(R.layout.sample3_get_font_metrics, R.string.title_get_font_metrics, R.layout.practice3_get_font_metrics))
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

        vpCustomViewPractice.currentItem = 12
    }

    private inner class PageModel(var sampleLayoutRes: Int,var titleRes:Int,var practiceLayoutRes: Int)
}