package com.dragger2.reactnativetest1022

import android.content.Context
import android.util.Log
import dalvik.system.DexFile
import java.io.File
import java.lang.reflect.Method

/**
 *  Description :热修复工具类

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/11/21
 */
class DxManager(private var context: Context) {
    private val TAG = "DxManager"

    fun loadDex(dexFilePath: File) {
        val optFile = File(context.cacheDir,dexFilePath.name)
        if (optFile.exists()){
            optFile.delete()
        }
        //加载dex
        val dexFile = DexFile.loadDex(dexFilePath.absolutePath,optFile.absolutePath,Context.MODE_PRIVATE)
        //遍历dex里的class
        val entry = dexFile.entries()
        while (entry.hasMoreElements()){
            val className = entry.nextElement()
            //修复好的realClass 找出bug class
            val realClass = dexFile.loadClass(className,context.classLoader)
            Log.d(TAG,"找到类："+className)

            //修复
            fix(realClass)
        }
    }

    private fun fix(realClass: Class<*>) {
        val methods = realClass.declaredMethods
        for (method in methods){
            //拿到注解
            val replace = method.getAnnotation(Replace::class.java) ?: continue
            val wrongClassName = replace.Class
            val wrongMethodName = replace.Method

            val wrongClass = Class.forName(wrongClassName)
            //拿到错误的Method对象
            val wrongMethod = wrongClass.getMethod(wrongMethodName, *method.parameterTypes)
            //修复
            replace(wrongMethod,method)
        }
    }

    private external fun replace(wrongMethod: Method, method: Method)
}