package com.dragger2.reactnativetest1022.base.api.interceptor

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.anima.DialogAnimas

/**
 * Created by wangru
 * Date: 2018/8/2  13:16
 * mail: 1902065822@qq.com
 * describe:
 */
class DialogCommon constructor(context: Context, val titleText: String = "", val contentText: String = "",
                               val leftText: String = "", val rightText: String = "",
                               val onLeftClick: (view: View) -> Unit = { }, val onRightClick: (view: View) -> Unit = { },
                               theme: Int = R.style.dialog_common) : AlertDialog(context, theme), DialogInterface {
    private lateinit var mDialogView: View
    //内容
    lateinit var tvTitle: TextView
    lateinit var tvContent: TextView
    lateinit var tvLeft: TextView
    lateinit var tvRight: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogView = View.inflate(context, R.layout.view_dialog_layout, null)
        setContentView(mDialogView)
        initView()
        initListener()
        setCanceledOnTouchOutside(false)
    }

    private fun initView() {
        tvTitle = mDialogView.findViewById(R.id.tvTitle)
        tvContent = mDialogView.findViewById(R.id.tvContent)
        tvLeft = mDialogView.findViewById(R.id.tvLeft)
        tvRight = mDialogView.findViewById(R.id.tvRight)

        tvTitle.visibility = if (TextUtils.isEmpty(titleText)) {
            tvContent.gravity = Gravity.CENTER
            View.GONE
        } else {
            tvContent.gravity = Gravity.CENTER
            tvTitle.text = titleText
            View.VISIBLE
        }
        tvContent.visibility = if (TextUtils.isEmpty(contentText)) View.GONE else {
            tvContent.text = contentText
            View.VISIBLE
        }
        if (!TextUtils.isEmpty(leftText)) tvLeft.text = leftText
        if (!TextUtils.isEmpty(rightText)) tvRight.text = rightText
    }

    private fun initListener() {
        setOnShowListener { DialogAnimas.startAnimaByFadeIns(mDialogView) }
        tvLeft.setOnClickListener {
            onLeftClick(it)
            dismiss()
        }
        tvRight.setOnClickListener{
            dismiss()
            onRightClick(it)
        }
    }
}