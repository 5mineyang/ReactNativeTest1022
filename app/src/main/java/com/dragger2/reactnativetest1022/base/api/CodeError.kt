package com.dragger2.reactnativetest1022.base.api

/**
 * Created by wangru
 * Date: 2018/6/30  14:29
 * mail: 1902065822@qq.com
 * describe:
 */

object CodeError {
    fun isSuc(code: Int): Boolean =
            code == SUC || code == SMS_SEND_SUC || code == UPDATE_PASS || code == UPDATE_PHONE

    /*    成功    */
    val SUC = 12000
    //短信发送成功
    val SMS_SEND_SUC = 21100
    val PHONE_REGISTERED = 22140
    //设置 修改密码成功
    val UPDATE_PASS = 23010
    //设置 换绑手机成功
    val UPDATE_PHONE = 24010

    /*    失败    */
    val FIAL = 13000
    //令牌过期
    val TOKEN_OUTTIME = 20022

    /*  登录注册  */
    //短信发送失败
    val SMS_SEND_FAIL = 33005
    //手机号不存在
    val SMS_SEND_NOEXIT = 33010
    //图形验证失败
    val CODE_IMG_FAIL = 33001

    /*  应应  */


    /*  职位  */


    /*  简历  */


    /*  消息  */


    /*  我的  */


}
