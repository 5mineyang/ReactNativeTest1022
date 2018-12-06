package com.dragger2.reactnativetest1022.base

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.multidex.MultiDex
import com.dragger2.reactnativetest1022.base.manager.ActivityStack


/**
 * BaseApplication 管理Activity，低内存处理
 * Created by wangru
 * Date: 2018/7/24  13:25
 * mail: 1902065822@qq.com
 * describe:
 */

open class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        activityListener()
    }

    override fun onTerminate() {
//        Logger.d("程序终止")
        super.onTerminate()
    }

    override fun onLowMemory() {
//        Logger.d("低内存")
        super.onLowMemory()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
//        Logger.d("配置改变")
        super.onConfigurationChanged(newConfig)
    }

    override fun onTrimMemory(level: Int) {
//        Logger.d("内存清理$level")
        super.onTrimMemory(level)
        if (level >= ComponentCallbacks2.TRIM_MEMORY_MODERATE) {
//            ImageLoader.loader().clearMemory(App.instance)
        }
    }

    fun activityListener() {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
//                Logger.d("onActivityCreated: " + activity.localClassName)
                ActivityStack.add(activity)
            }

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
//                Logger.d("onActivityDestroyed: " + activity.localClassName)
                ActivityStack.finish(activity)
            }
        })
    }

    fun exitApp() {
        ActivityStack.showAll()
        ActivityStack.finishAll()
        System.exit(0)
    }
}
