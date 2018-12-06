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
class CustomViewPractice4 : BaseActivity() {
    private var mViewPagerList = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        mViewPagerList.add(PageModel(R.layout.sample4_clip_rect, R.string.title_clip_rect, R.layout.practice4_clip_rect))
        mViewPagerList.add(PageModel(R.layout.sample4_clip_path, R.string.title_clip_path, R.layout.practice4_clip_path))
        mViewPagerList.add(PageModel(R.layout.sample4_translate, R.string.title_translate, R.layout.practice4_translate))
        mViewPagerList.add(PageModel(R.layout.sample4_scale, R.string.title_scale, R.layout.practice4_scale))
        mViewPagerList.add(PageModel(R.layout.sample4_rotate, R.string.title_rotate, R.layout.practice4_rotate))
        mViewPagerList.add(PageModel(R.layout.sample4_skew, R.string.title_skew, R.layout.practice4_skew))
        mViewPagerList.add(PageModel(R.layout.sample4_matrix_translate, R.string.title_matrix_translate, R.layout.practice4_matrix_translate))
        mViewPagerList.add(PageModel(R.layout.sample4_matrix_scale, R.string.title_matrix_scale, R.layout.practice4_matrix_scale))
        mViewPagerList.add(PageModel(R.layout.sample4_matrix_rotate, R.string.title_matrix_rotate, R.layout.practice4_matrix_rotate))
        mViewPagerList.add(PageModel(R.layout.sample4_matrix_skew, R.string.title_matrix_skew, R.layout.practice4_matrix_skew))
        mViewPagerList.add(PageModel(R.layout.sample4_camera_rotate, R.string.title_camera_rotate, R.layout.practice4_camera_rotate))
        mViewPagerList.add(PageModel(R.layout.sample4_camera_rotate_fixed, R.string.title_camera_rotate_fixed, R.layout.practice4_measure_text))
        mViewPagerList.add(PageModel(R.layout.sample4_camera_rotate_hitting_face, R.string.title_camera_rotate_hitting_face, R.layout.practice4_camera_rotate_hitting_face))
        mViewPagerList.add(PageModel(R.layout.sample4_flipboard, R.string.title_flipboard, R.layout.practice4_flipboard))
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