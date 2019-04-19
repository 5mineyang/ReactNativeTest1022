package com.dragger2.reactnativetest1022.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.dragger2.reactnativetest1022.R
import kotlinx.android.synthetic.main.custom_bottomdialog.view.*


/**
 *  Description :抖音评论弹出框

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/12/13
 */
class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var mBehavior: BottomSheetBehavior<View>
    private lateinit var mContext: Context
    private lateinit var mRootView: View     //布局View
    private var mHeight = 0       //弹框高度

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //设置背景不变暗
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.TransBottomSheetDialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        mContext = context!!
        mRootView = View.inflate(mContext, R.layout.custom_bottomdialog, null)
        dialog.setContentView(mRootView)

        //设置透明背景
        dialog.window.findViewById<FrameLayout>(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT)

        //布局管理器 设置view高度
        val layoutParams = mRootView.layoutParams
        //设置百分比高度
        val height = mContext.resources.displayMetrics.heightPixels * 0.6
        layoutParams.height = height.toInt()
        mRootView.layoutParams = layoutParams

        mBehavior = BottomSheetBehavior.from(mRootView.parent as View)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        //默认全屏展开
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        //跳过折叠状态
        mBehavior.skipCollapsed = true

        initListener()
    }

    private fun initListener() {
        mRootView.llYingyingCompanyTop.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }

    fun doclick(v: View) {
        //点击任意布局关闭
        mBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }
}
