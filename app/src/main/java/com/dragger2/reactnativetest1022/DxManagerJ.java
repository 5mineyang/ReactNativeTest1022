package com.dragger2.reactnativetest1022;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/21
 */

public class DxManagerJ {
    private Context context;
    private final static String TAG = "DxManagerJ";

    public DxManagerJ(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePath) {
        File optFile = new File(context.getCacheDir(), dexFilePath.getName());
        if (optFile.exists()) {
            optFile.delete();
        }
        try {
            //加载dex
            DexFile dexFile = DexFile.loadDex(dexFilePath.getName(), optFile.getName(), Context.MODE_PRIVATE);
            //遍历dex里的class
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()) {
                String className = entry.nextElement();
                //修复好的realClass 找出bug class
                Class realClass = dexFile.loadClass(className, context.getClassLoader());
                //修复
                fix(realClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fix(Class realClass) {
        Method methods[] = realClass.getDeclaredMethods();
        //遍历类
        for (Method method : methods) {
            //拿到注解
            Replace replace = method.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String wrongClassName = replace.Class();
            String wrongMethodName = replace.Method();

            Class wrongClass = null;
            try {
                wrongClass = Class.forName(wrongClassName);
                //拿到错误的Method对象
                Method wrongMethod = wrongClass.getMethod(wrongMethodName, method.getParameterTypes());
                //修复
//                replace(wrongMethod, method);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

//    private native void replace(Method wrongMethod, Method method);
}
