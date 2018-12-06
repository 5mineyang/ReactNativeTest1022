package com.youke.yingba.base.greendao.cache

import com.dragger2.reactnativetest1022.base.greendao.bean.CacheInfoBean
import com.dragger2.reactnativetest1022.base.greendao.ope.CacheInfoDaoOpe
import com.dragger2.reactnativetest1022.base.json.GsonConvert
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 缓存数据和取缓存数据
 * Created by wr
 * Date: 2018/5/10  15:26
 * mail: 1902065822@qq.com
 * describe:
 */

object CacheDataRequest {
    private fun saveCacheCommon(cacheKey: String, infoCall: () -> String, onSucCallBack: () -> Unit, onFailCallBack: () -> Unit = {}) {
        Observable.create(ObservableOnSubscribe<CacheInfoBean> { emitter ->
            val cacheInfo = CacheInfoBean()
            cacheInfo.timeCreate = System.currentTimeMillis()
            cacheInfo.key = cacheKey
            cacheInfo.data = infoCall()
            CacheInfoDaoOpe.insertOrUpdateVersionByKey(cacheKey, cacheInfo)
            emitter.onNext(cacheInfo)
            emitter.onComplete()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onSucCallBack() },
                        { onFailCallBack() }
                )
    }

    private fun <I> getCacheCommon(cacheKey: String, change: (result: String) -> I, onSucCallBack: (result: I) -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
            val data = CacheInfoDaoOpe.getCacheInfoDataByKey(cacheKey)
            emitter.onNext(data)
            emitter.onComplete()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            onSucCallBack(change(result))
                        },
                        { onFailCallBack() }
                )
    }

    //String
    fun <I> saveCacheString(cacheKey: String, info: String, onSucCallBack: () -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        saveCacheCommon(cacheKey, { info }, onSucCallBack, onFailCallBack)
    }

    fun <I> getCacheString(cacheKey: String, onSucCallBack: (result: String) -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        getCacheCommon(cacheKey, { it }, onSucCallBack, onFailCallBack)
    }

    //Bean
    fun <I> saveCacheBean(cacheKey: String, info: I, onSucCallBack: () -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        saveCacheCommon(cacheKey, { Gson().toJson(info) }, onSucCallBack, onFailCallBack)
    }

    fun <I> getCacheBean(cacheKey: String, type: Class<I>, onSucCallBack: (result: I) -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        getCacheCommon(cacheKey, { Gson().fromJson(it, type) }, onSucCallBack, onFailCallBack)
    }

    //BeanList
    fun <I> saveCacheBeanList(cacheKey: String, info: List<I>?, onSucCallBack: () -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        saveCacheCommon(cacheKey, { Gson().toJson(info) }, onSucCallBack, onFailCallBack)
    }

    fun <I> getCacheBeanList(cacheKey: String, type: Class<I>, onSucCallBack: (result: List<I>) -> Unit = {}, onFailCallBack: () -> Unit = {}) {
        getCacheCommon(cacheKey, { GsonConvert.jsonToBeanList(it, type) }, onSucCallBack, onFailCallBack)
    }
}
