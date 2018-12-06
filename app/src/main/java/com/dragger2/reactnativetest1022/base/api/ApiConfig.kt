package com.dragger2.reactnativetest1022.base.api

import com.dragger2.reactnativetest1022.BuildConfig

/**
 * Created by wangru
 * Date: 2018/7/27  8:45
 * mail: 1902065822@qq.com
 * describe:
 */
object ApiConfig {
    //接口根地址
    const val BASE_URL = BuildConfig.SERVER_IP

    //极验API1地址
    const val CAPTCHA_URL = ApiConfig.BASE_URL + "api/geetest_app/geetest_app_aptcha"
    //极验API2地址
    const val VALIDATE_URL = ApiConfig.BASE_URL + "api/geetest_validate/doBefore_app_reg"

    //发送短信验证码
    const val VALIDATE_URL_SEND_CODE = ApiConfig.BASE_URL + "api/sms/sms_app_send"

    //设置超时时间
    const val DEFAULT_TIMEOUT = 10_000L
    //上传超时时间
    const val UPLOAD_TIMEOUT = 20_000L
    //下载超时时间
    const val DOWNLOAD_TIMEOUT = 20_000L


}