package com.dragger2.reactnativetest1022.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.dragger2.reactnativetest1022.R


/**
 * Created by wr
 * Date: 2018/8/28  19:53
 * describe:
 */
abstract class BaseDialogFragment : DialogFragment() {
    protected lateinit var mRootView: View
    protected var mActivity: Activity? = null
    protected var mIsInitView = false
    protected var mIsBackCancelable = true//返回能取消

    companion object {
        val mTag = javaClass.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            mActivity = context
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置style
        setStyle(STYLE_NORMAL, R.style.BaseDialogFragment)

//        dialog?.setCanceledOnTouchOutside(false)//设置点击外部不可取消
//        isCancelable = false //设置不可取消
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //去除标题栏
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        initTop()
        mRootView = inflater.inflate(bindLayout(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        mIsInitView = true
        initValue()
        initListener()
        initThis()
    }

    private fun initThis() {
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            // KEYCODE_BACK 拦截返回true
            keyCode == KeyEvent.KEYCODE_BACK && !mIsBackCancelable
        }
    }

    override fun onStart() {
        super.onStart()
        //设置 dialog 的宽高
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //设置 dialog 的背景为 null
        dialog.window?.setBackgroundDrawable(null)
    }

    protected abstract fun bindLayout(): Int
    protected open fun initTop() {}
    protected open fun initView() {}
    protected open fun initValue() {}
    protected open fun initData() {}
    protected open fun initListener() {}

    override fun show(manager: FragmentManager, tag: String) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss()
    }

    fun isShowing(): Boolean {
        return dialog != null && dialog.isShowing
    }

    fun show(manager: FragmentManager?) {
        if (manager == null) return
        show(manager, mTag)
    }

    //设置外面透明
    fun setOutBackgroundTransparent() {
        dialog.window?.let {
            val windowParams = it.attributes
            windowParams.dimAmount = 0.0f
            it.attributes = windowParams
        }
    }

    //设置返回键不取消
    fun setIsBackCanceled(isBackCancelable: Boolean) {
        mIsBackCancelable = isBackCancelable
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}