package com.dragger2.reactnativetest1022.base.view

import android.view.Gravity
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.BaseDialogFragment


/**
 * Created by wr
 * Date: 2018/8/28  20:03
 * describe:
 */
class LoadingDialogFragment : BaseDialogFragment() {

    override fun bindLayout(): Int = R.layout.dialog_loading
    override fun initTop() {
        super.initTop()
        dialog.window?.attributes?.gravity = Gravity.CENTER
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onStart() {
        super.onStart()
        setOutBackgroundTransparent()
    }
}