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
class CustomViewPractice2 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample2_linear_gradient, R.string.title_linear_gradient, R.layout.practice2_linear_gradient))
        mViewPagerList.add(PageModel(R.layout.sample2_radial_gradient, R.string.title_radial_gradient, R.layout.practice2_radial_gradient))
        mViewPagerList.add(PageModel(R.layout.sample2_sweep_gradient, R.string.title_sweep_gradient, R.layout.practice2_sweep_gradient))
        mViewPagerList.add(PageModel(R.layout.sample2_bitmap_shader, R.string.title_bitmap_shader, R.layout.practice2_bitmap_shader))
        mViewPagerList.add(PageModel(R.layout.sample2_compose_shader, R.string.title_compose_shader, R.layout.practice2_compose_shader))
        mViewPagerList.add(PageModel(R.layout.sample2_lighting_color_filter, R.string.title_lighting_color_filter, R.layout.practice2_lighting_color_filter))
        mViewPagerList.add(PageModel(R.layout.sample2_color_mask_color_filter, R.string.title_color_matrix_color_filter, R.layout.practice2_color_matrix_color_filter))
        mViewPagerList.add(PageModel(R.layout.sample2_xfermode, R.string.title_xfermode, R.layout.practice2_xfermode))
        mViewPagerList.add(PageModel(R.layout.sample2_stroke_cap, R.string.title_stroke_cap, R.layout.practice2_stroke_cap))
        mViewPagerList.add(PageModel(R.layout.sample2_stroke_join, R.string.title_stroke_join, R.layout.practice2_stroke_join))
        mViewPagerList.add(PageModel(R.layout.sample2_stroke_miter, R.string.title_stroke_miter, R.layout.practice2_stroke_miter))
        mViewPagerList.add(PageModel(R.layout.sample2_path_effect, R.string.title_path_effect, R.layout.practice2_path_effect))
        mViewPagerList.add(PageModel(R.layout.sample2_shadow_layer, R.string.title_shader_layer, R.layout.practice2_shadow_layer))
        mViewPagerList.add(PageModel(R.layout.sample2_mask_filter, R.string.title_mask_filter, R.layout.practice2_mask_filter))
        mViewPagerList.add(PageModel(R.layout.sample2_fill_path, R.string.title_fill_path, R.layout.practice2_fill_path))
        mViewPagerList.add(PageModel(R.layout.sample2_text_path, R.string.title_text_path, R.layout.practice2_text_path))
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

        vpCustomViewPractice.currentItem = 13
    }

    private inner class PageModel(var sampleLayoutRes: Int,var titleRes:Int,var practiceLayoutRes: Int)
}