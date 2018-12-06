package com.dragger2.reactnativetest1022.base.bean

import java.io.Serializable

/**
 * @author fengjing:
 * @Description 版本信息类
 * @Date 2015年9月18日 上午10:01:28
 * @Email 164303256@qq.com
 */
class VersionBean : Serializable {
    /**
     * 版本号
     */
    var version: Int = 0

    /**
     * 更新地址
     */
    var url: String? = null

    /**
     * 版本名
     */
    var name: String? = null

    /**
     * 版本更新描述
     */
    var intro: String? = null

    var type: Int = 0//1 强制更新
}
