package com.dragger2.reactnativetest1022.base.bean

import java.io.Serializable

/**
 * Created by wangru
 * Date: 2018/6/29  10:40
 * mail: 1902065822@qq.com
 * describe:
 */

class UserInfo(var accessToken: String = "", var resumeStatus: Int = 0, var user: UserBean = UserBean()) : Serializable {
    //resumeStatus  没有创建文本简历:1，没有创建视频简历:2，没有完善文本简历:3，已经完善文本和视频简历:4
    class UserBean(var avatar: String = "", var state: Int = 0, var phone: String = "", var username: String = "",
                   var huanxName: String = "", var huanxPassword: String = "", var usertype: Int = 0, var id: Int = 0) : Serializable
}