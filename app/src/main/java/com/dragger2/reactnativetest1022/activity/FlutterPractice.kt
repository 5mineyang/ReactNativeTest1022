package com.dragger2.reactnativetest1022.activity

import android.os.Bundle
import com.dragger2.reactnativetest1022.R
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 *  Description : flutter页面练习

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2019/4/19
 */
class FlutterPractice : SwipeBackActivity() {
    private var mSwipeBackLayout: SwipeBackLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true)
        mSwipeBackLayout = swipeBackLayout
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout!!.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        mSwipeBackLayout!!.setEdgeSize(200)

//        val flutterView = Flutter.createView(this,lifecycle,"route1")
//        setContentView(flutterView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //返回加动画
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right)
    }
}