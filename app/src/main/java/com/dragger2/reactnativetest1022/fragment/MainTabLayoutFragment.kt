package com.dragger2.reactnativetest1022.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragger2.reactnativetest1022.R
import kotlinx.android.synthetic.main.fragment_viewpager_view.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/26
 */
class MainTabLayoutFragment : Fragment() {
    private var mTitlesString = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_viewpager_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
        initListener()
    }

    private fun initData() {

    }

    private fun initView() {
        tvMainTableLayoutViewPagerContent.text = mTitlesString

        initAdapter()
    }

    //监听事件
    fun initListener() {

    }

    //适配器
    private fun initAdapter() {

    }

    //外部调用 设置想要内容
    fun setContent(content: String) {
        mTitlesString =content
    }
}