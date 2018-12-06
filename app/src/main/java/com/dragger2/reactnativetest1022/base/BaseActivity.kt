package com.dragger2.reactnativetest1022.base

import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import com.dragger2.reactnativetest1022.R
import com.dragger2.reactnativetest1022.base.view.LoadingDialogFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity


/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/10/24
 */

abstract class BaseActivity : SwipeBackActivity() {
    private var mSwipeBackLayout: SwipeBackLayout? = null
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mLoadingDialog by lazy { LoadingDialogFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            savedInstanceState.putParcelable("android:support:fragments", null)
        }
        super.onCreate(savedInstanceState ?: Bundle())
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true)

        mSwipeBackLayout = swipeBackLayout

        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout!!.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)

        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        mSwipeBackLayout!!.setEdgeSize(200)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initTop()
        setContentView(bindLayout())
        initData()
        initView()
        initValue()
        initListener()
    }

    abstract fun bindLayout(): Int
    open fun initTop() {}
    open fun initView() {}
    open fun initValue() {}
    open fun initData() {}
    open fun initListener() {}

    /**
     * @param isCanCancel 是否能被取消
     */
    fun showLoadingDialog(isCanCancel: Boolean = true) {
        if (!mLoadingDialog.isShowing()) {
//            mLoadingDialog.isCancelable=isCanCancel
            mLoadingDialog.show(supportFragmentManager)
        }
    }

    fun dismissLoadingDialog() {
        mLoadingDialog.dismissAllowingStateLoss()
    }

    fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //返回加动画
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right)
    }
}