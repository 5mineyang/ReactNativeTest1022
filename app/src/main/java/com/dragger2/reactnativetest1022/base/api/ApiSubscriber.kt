package com.dragger2.reactnativetest1022.base.api

import android.app.Activity
import android.support.v4.app.Fragment
import com.dragger2.reactnativetest1022.base.BaseActivity
import com.dragger2.reactnativetest1022.base.BaseFragment
import com.dragger2.reactnativetest1022.base.by.Weak
import com.dragger2.reactnativetest1022.base.utils.ApiException
import com.google.gson.JsonSyntaxException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by wr
 * Date: 2018/6/28  19:38
 * mail: 1902065822@qq.com
 * describe:
 */

open class ApiSubscriber<T>(var mIsShowLoad: Boolean, var isCanCancel: Boolean = true) : Observer<T> {
    private var mFragment: BaseFragment? by Weak()
    private var mActivity: BaseActivity? by Weak()

    override fun onSubscribe(d: Disposable) {
        mFragment?.addSubscription(d)
        mActivity?.addSubscription(d)
        if (mIsShowLoad) {
            mFragment?.showLoadingDialog(isCanCancel)
            mActivity?.showLoadingDialog(isCanCancel)
        }
    }

    constructor(context: Activity, isShowLoad: Boolean = false, isCanCancel: Boolean = true) : this(isShowLoad, isCanCancel) {
        mActivity = context as?BaseActivity
//        if (mActivity == null) Logger.d("ApiSubscriber#activity没有集成BaseActivity,退出时取消请求需要手动添加onSubscribe")
    }


    constructor(context: Fragment, isShowLoad: Boolean = false, isCanCancel: Boolean = true) : this(isShowLoad, isCanCancel) {
        mFragment = context as?BaseFragment
//        if (mFragment == null) Logger.d("ApiSubscriber#fragment没有集成BaseFragment,退出时取消请求需要手动添加onSubscribe")
    }

    constructor() : this(false) {
    }

    override fun onNext(result: T) {
        dismissLoad()
    }

    override fun onError(e: Throwable) {
        onError(e, null)
    }

    fun onError(e: Throwable, apiError: (() -> Unit)? = null) {
        dismissLoad()
        if (e is ApiException) {
            //处理服务器返回的错误
            if (apiError != null) {
                apiError()
            } else {
//                App.toast.errorNet(e)
            }
        } else if (e is ConnectException || e is UnknownHostException) {
//            App.toast.error("网络未连接")
        } else if (e is TimeoutException || e is SocketTimeoutException) {
//            App.toast.error("网络超时")
        } else if (e is JsonSyntaxException) {
//            App.toast.error("数据解析异常")
        } else {
//            Logger.d("onError#${e.message}")
//            App.toast.error("未知异常${e.message}")
        }
        e.printStackTrace()
    }

    override fun onComplete() {
        dismissLoad()
        mFragment = null
        mActivity = null
    }

    private fun dismissLoad() {
        if (mIsShowLoad) {
            mFragment?.dismissLoadingDialog()
            mActivity?.dismissLoadingDialog()
        }
    }

}
