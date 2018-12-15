package com.dragger2.reactnativetest1022.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.bean.TabEntity
import com.dragger2.reactnativetest1022.fragment.MainTabLayoutFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.flyco.tablayout.utils.UnreadMsgUtils
import kotlinx.android.synthetic.main.activity_tablayout_main.*
import java.util.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/12/14
 */
class MainTabLayoutActivity : BaseActivity() {
    private val mTitles = arrayOf("首页", "消息", "联系人", "更多")  //标题
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.tab_contact_select, R.mipmap.tab_more_select)    //已选择图标
    private val mIconUnselectIds = intArrayOf(R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect)  //未选择图标
    private val mTabEntitesList = ArrayList<CustomTabEntity>()  //下面菜单里存放的东西（菜单名，选中图标，未选中图标）
//    private lateinit var mAdapter: FragmentAdapter
    private var mFragmentList = ArrayList<Fragment>()
    private val random = Random()

    override fun bindLayout(): Int {
        return R.layout.activity_tablayout_main
    }

    override fun initData() {
        super.initData()

        //把tabLayout用到的数据装进去
        mTitles.forEachIndexed { index, _ ->
            mTabEntitesList.add(TabEntity(mTitles[index], mIconSelectIds[index], mIconUnselectIds[index]))
        }

        //加4个fragment
        for (i in 0 until 4) {
            mFragmentList.add(MainTabLayoutFragment())
            var content = ""
            when (i) {
                0 -> content = "首页"
                1 -> content = "消息"
                2 -> content = "联系人"
                3 -> content = "更多"
            }
            //设置不同的内容
            (mFragmentList[i] as MainTabLayoutFragment).setContent(content)
        }
    }

    override fun initView() {
        super.initView()
        //数据绑定tabLayout
        tlMainTabLayout.setTabData(mTabEntitesList,this,R.id.flMainTalLayout,mFragmentList)

        //图标上添加未读消息
        tlMainTabLayout.showMsg(0, 55)
        //设置未读消息距离与图标
        tlMainTabLayout.setMsgMargin(0, -5f, 5f)

        tlMainTabLayout.showMsg(1, 100)
        tlMainTabLayout.setMsgMargin(1, -5f, 5f)

        //图标上加小点
        tlMainTabLayout.showDot(2)
        val tl_2 = tlMainTabLayout.getMsgView(2)
        if (tl_2 != null) {
            UnreadMsgUtils.setSize(tl_2, 30)
        }

        tlMainTabLayout.showMsg(3, 2)
        tlMainTabLayout.setMsgMargin(3, 0f, 5f)
        val tl_3 = tlMainTabLayout.getMsgView(3)
        if (tl_3 != null) {
            //设置未读消息背景颜色
            tl_3.backgroundColor = Color.parseColor("#6D8FB0")
        }

        initAdapter()
    }

    override fun initListener() {
        super.initListener()

//        //viewPager滑动事件
//        vpMainTabLayout.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                //tabLayout跟着动
//                tlMainTabLayout.currentTab = position
//            }
//        })

        //tabLayout点击事件
        tlMainTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            //点击事件
            override fun onTabSelect(position: Int) {
//                vpMainTabLayout.currentItem = position
            }

            //重复点击事件
            override fun onTabReselect(position: Int) {
                if (position == 0) {
                    //让首页未读消息变化
                    tlMainTabLayout.showMsg(0, random.nextInt(100) + 1)
                }
            }
        })
    }

    //适配器初始化
    private fun initAdapter() {
//        mAdapter = FragmentAdapter(supportFragmentManager, mFragmentList)
//        vpMainTabLayout.adapter = mAdapter
    }
}