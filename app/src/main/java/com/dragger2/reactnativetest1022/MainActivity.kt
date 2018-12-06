package com.dragger2.reactnativetest1022

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dragger2.reactnativetest1022.activity.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/10/24
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()
    }

    private fun initView() {
//        SophixManager.getInstance().queryAndLoadNewPatch()
    }

    private fun initListener() {
        //下一页
        tvToNext.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //图片线程显示Loading
        tvToNextLoadingOne.setOnClickListener {
            val intent = Intent(this, LoadingOneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //动画循环播放Loading
        tvToNextLoadingTwo.setOnClickListener {
            val intent = Intent(this, LoadingTwoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //绘制view练习
        tvToNextViewPractice.setOnClickListener {
            val intent = Intent(this, CustomViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //绘制layout练习
        tvToNextLayoutPractice.setOnClickListener {
            val intent = Intent(this, CustomLayoutActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //计算按钮
        btnAndFixCalculation.setOnClickListener {
            val caculutor = Caculutor()
            tvAndFix.text = "${caculutor.caculutor()}"
        }

        //恢复bug按钮
        btnAndFixRecovery.setOnClickListener {

        }

        //分享页面
        tvToNextShare.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //Glide练习页面
        tvToNextGlide.setOnClickListener {
            val intent = Intent(this, GlideActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //topView练习页面
        tvToNextNestedScrollView.setOnClickListener {
            val intent = Intent(this, NestedScrollViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        //左上角菜单点击
        main_menu.setOnClickListener {
            if (activity_na.isDrawerOpen(nav)) {
                activity_na.closeDrawer(nav)
            } else {
                activity_na.openDrawer(nav)
            }
        }

        //弹出菜单滑动
        nav.setNavigationItemSelectedListener {
            activity_na.closeDrawer(nav)
            true
        }
    }
}
