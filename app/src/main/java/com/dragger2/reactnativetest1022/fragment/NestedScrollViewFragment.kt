package com.dragger2.reactnativetest1022.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.adapter.CommonAdapter
import kotlinx.android.synthetic.main.fragment_nestedscrollview.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/26
 */
class NestedScrollViewFragment : Fragment() {
    private lateinit var mLayoutManager: GridLayoutManager
    private lateinit var mAdapter: CommonAdapter<String>
    private var mList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_nestedscrollview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
        initListener()
    }

    private fun initView() {
        initAdapter()
    }

    //监听事件
    fun initListener() {

    }

    //适配器
    private fun initAdapter() {
        mLayoutManager = GridLayoutManager(context, 2)
        rvNestedScrollViewViewPager.layoutManager = mLayoutManager
        mAdapter = CommonAdapter(context, R.layout.fragment_nestedscrollview_item, mList, holderConvert = { holder, t, _, _ ->
            holder.apply {
                setText(R.id.tvNestedScrollViewViewPagerRv, t)
            }
        })
        rvNestedScrollViewViewPager.adapter = mAdapter
    }

    private fun initData() {
        for (i in 0..50) {
            mList.add("数据" + i)
        }
    }
}
