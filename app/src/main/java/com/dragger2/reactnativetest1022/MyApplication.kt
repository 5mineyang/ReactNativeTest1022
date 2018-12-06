package com.dragger2.reactnativetest1022

import android.app.Application
import android.util.Log
import com.taobao.sophix.PatchStatus
import com.taobao.sophix.SophixManager
import com.taobao.sophix.listener.PatchLoadStatusListener

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/21
 */
class MyApplication : Application() {
    private val TAG = "MyApplication"

    override fun onCreate() {
        super.onCreate()

        initFix()
    }

    fun initFix(){
        val appVersion = this.packageManager.getPackageInfo(packageName,0).versionName
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub (object : PatchLoadStatusListener{
                    override fun onLoad(mode: Int, code: Int, info: String?, handlePatchVersion: Int) {
                        //补丁加载回调通知
                        if(code == PatchStatus.CODE_LOAD_SUCCESS){
                            Log.d(TAG,"修复成功"+info)
                        }else if(code == PatchStatus.CODE_LOAD_RELAUNCH){   //补丁下载成功
                            Log.d(TAG,"重启成功"+info)
                        }else{   //其他
                            Log.d(TAG,"其他"+info)
                        }
                    }
                }).initialize()
    }
}