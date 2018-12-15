package com.dragger2.reactnativetest1022.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *  Description :

 *  Author:yang

 *  Email:1318392199@qq.com

 *  Date: 2018/12/14
 */
class TabEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}