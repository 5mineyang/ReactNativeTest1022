package com.dragger2.reactnativetest1022.base.api

import com.dragger2.reactnativetest1022.BuildConfig
import com.dragger2.reactnativetest1022.base.api.interceptor.LogInterceptor
import com.dragger2.reactnativetest1022.base.api.interceptor.SealAccountInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by wangru
 * Date: 2018/6/28  19:03
 * mail: 1902065822@qq.com
 * describe:
 */

class RequestManager() {
    private val retrofit: Retrofit
    private val retrofitToken: Retrofit
    private val retrofitTokenNoParams: Retrofit

    private object SingletonHolder {
        val INSTANCE = RequestManager()
    }

    init {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(ApiConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(CommonInterceptor())
            addInterceptor(LogInterceptor())
            addInterceptor(SealAccountInterceptor())
        }.build()

        retrofit = Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val clientToken = OkHttpClient.Builder().apply {
            connectTimeout(ApiConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(CommonInterceptor(true))
            addInterceptor(LogInterceptor())
            addInterceptor(SealAccountInterceptor())
        }.build()
        retrofitToken = Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(clientToken)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val clientTokenNoParams = OkHttpClient.Builder().apply {
            connectTimeout(ApiConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(CommonInterceptor(true,false))
            addInterceptor(LogInterceptor())
            addInterceptor(SealAccountInterceptor())
        }.build()

        retrofitTokenNoParams = Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(clientTokenNoParams)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getBasicParams(): Interceptor = CommonInterceptor()

    //这里返回一个泛型类，主要返回的是定义的接口类
    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    fun <T> createServiceWithToken(clazz: Class<T>): T {
        return retrofitToken.create(clazz)
    }

    fun <T> createServiceWithNoParams(clazz: Class<T>): T {
        return retrofitTokenNoParams.create(clazz)
    }

    companion object {
        val instance: RequestManager
            get() = SingletonHolder.INSTANCE
        val instanceApi: ApiService
            get() = SingletonHolder.INSTANCE.createService(ApiService::class.java)
//        val instanceResume: ApiServiceResume
//            get() = SingletonHolder.INSTANCE.createServiceWithToken(ApiServiceResume::class.java)
//        val instanceTokenNoParams: ApiServiceResume
//            get() = SingletonHolder.INSTANCE.createServiceWithNoParams(ApiServiceResume::class.java)
    }

    private inner class CommonInterceptor(var isToken: Boolean = false, var isParams: Boolean = true) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder().apply {
                if (isParams) {
                    //版本号
                    addQueryParameter("ver", "${BuildConfig.VERSION_CODE}")
                    //平台 1 android 2 ios
                    addQueryParameter("os", "1")
                    //手机宽高
//                    addQueryParameter("px", "${AppInfoData.width}x${AppInfoData.height}")
                }
            }.build()

            // Request customization: add request headers
            //请求添加头部
            val requestBuilder = original.newBuilder().apply {
                if (isToken) {
//                    addHeader("accessToken", UserData.token)
                }
            }.url(url)

            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}
