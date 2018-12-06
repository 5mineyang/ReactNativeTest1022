package com.dragger2.reactnativetest1022.activity

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.fragment.LayoutPageFragment
import kotlinx.android.synthetic.main.activity_customview_practice.*
import java.util.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/8
 */
class CustomLayoutPractice1 : BaseActivity() {
    private var pageModels = ArrayList<PageModel>()

    override fun bindLayout(): Int {
        return R.layout.activity_customview_practice
    }

    override fun initData() {
        super.initData()

        pageModels.add(PageModel(R.layout.sample2_1_square_image_view, R.string.title_square_image_view, R.layout.practice2_1_square_image_view))
    }

    override fun initView() {
        super.initView()

        vpCustomViewPractice.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return LayoutPageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }
        tlCustomViewPractice.setupWithViewPager(vpCustomViewPractice)
    }

    private inner class PageModel internal constructor(@param:LayoutRes @field:LayoutRes internal var sampleLayoutRes: Int, @param:StringRes @field:StringRes internal var titleRes: Int, @param:LayoutRes @field:LayoutRes internal var practiceLayoutRes: Int)
}