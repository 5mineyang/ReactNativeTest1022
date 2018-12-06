package com.dragger2.reactnativetest1022.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dragger2.reactnativetest1022.base.view.LoadingDialogFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.functions.Functions
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseFragment(private var isInject: Boolean = false) : Fragment(), IBase, EasyPermissions.PermissionCallbacks {
    protected lateinit var mRootView: View
    //是否对用户可见
    protected var mIsVisible: Boolean = false
    // 是否加载完成 当执行完onCreateView,View的初始化方法后方法后即为true
    protected var mIsPrepare: Boolean = false
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val mLoadingDialog by lazy { LoadingDialogFragment() }

    protected var mActivity: Activity? = null
    private var mBaseActivity: BaseActivity? = null
    //函数的集合
    protected var mFunctions: Functions? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            mActivity = context
        }
        if (context is BaseActivity) {
            mBaseActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(bindLayout(), container, false)
        initTop()
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        mIsPrepare = true
        initListener()
        initValue()
        prepareFetchData()
    }

    protected abstract fun bindLayout(): Int
    override fun initTop() {}
    override fun initData() {}
    override fun initView() {}
    override fun initValue() {}
    override fun initListener() {}

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        this.mIsVisible = !hidden
        if (!activity.isFinishing && !activity.isDestroyed) {
            if (hidden) onInVisibleToUser() else prepareFetchData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.mIsVisible = isVisibleToUser
        if (mIsVisible) prepareFetchData() else onInVisibleToUser()
    }

    private fun prepareFetchData() {
        if (mIsVisible && mIsPrepare) {
            onLazyLoad()
        }
    }

    //用户可见时执行的操作
    private fun onVisibleToUser() = if (mIsPrepare) onLazyLoad() else {
    }

    //用户不可见时执行的操作
    private fun onInVisibleToUser() = if (mIsPrepare) onLazyClear() else {
    }

    // 懒加载，仅当用户可见切view初始化结束后才会执行
    protected open fun onLazyLoad() {
//        Logger.i("${this.javaClass.simpleName}#onLazyLoad")
    }

    //不可见时清理
    protected open fun onLazyClear() {
//        Logger.i("${this.javaClass.simpleName}#onLazyClear")
    }

    /**
     * @param isCanCancel 是否能被取消
     */
    fun showLoadingDialog(isCanCancel: Boolean = true) {
        if (!mLoadingDialog.isShowing()) {
//            mLoadingDialog.isCancelable=isCanCancel
            mLoadingDialog.show(activity!!.supportFragmentManager)
        }
    }

    fun dismissLoadingDialog() {
        mLoadingDialog.dismissAllowingStateLoss()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //选择照片（图库，拍照）
//        SimplePhotoUtil.instance.onPhotoResult(requestCode, resultCode, data)
    }

    //activity调用此方法进行设置Functions
    fun setFunctions(functions: Functions) {
        this.mFunctions = functions
    }

    override fun onDestroy() {
        mIsPrepare = false
        mCompositeDisposable.clear()
        super.onDestroy()
    }
}