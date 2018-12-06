package com.dragger2.reactnativetest1022.base.api

import com.app.common.json.GsonUtil
import com.dragger2.reactnativetest1022.base.bean.BaseBean
import com.dragger2.reactnativetest1022.base.utils.ApiException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wangru
 * Date: 2018/6/28  19:12
 * mail: 1902065822@qq.com
 * describe:
 */

class DefaultTransformer<T> : ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t ->
                    var code = 12000
                    var msg = ""
                    when (t) {
                        is BaseBean<*> -> {
                            code = t.code
                            msg = t.msg
                        }
                    }
                    if (!CodeError.isSuc(code)) {
                        throw ApiException(code, msg, GsonUtil().toJson(t))
                    }
                    t
                }
    }

    companion object {
        fun <T> create(): DefaultTransformer<T> {
            return DefaultTransformer()
        }
    }
}