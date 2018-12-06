package com.dragger2.reactnativetest1022.base.api

import com.dragger2.reactnativetest1022.base.bean.DataVideoBean
import com.dragger2.reactnativetest1022.base.bean.BaseBean
import com.dragger2.reactnativetest1022.base.bean.VersionBean
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.*


/**
 * 所有请求
 * Created by wangru
 * Date: 2018/7/6  15:02
 * mail: 1902065822@qq.com
 * describe:
 */
interface ApiService {
    //------------   测试------------
    @GET("http://www.zuzuapp.com:18080/zuzu/version/checkForceVersion")
    fun update(@Query("user_version") version: String): Observable<VersionBean>

    @Streaming
    @GET
    fun downLoadFile(@NonNull @Url url: String): Observable<ResponseBody>

    @Multipart
    @POST("{url}")
    fun uploadFile(@Path(value = "url", encoded = true) url: String, @Part file: RequestBody): Observable<ResponseBody>

    @GET("http://120.76.205.241:8000/post/baisibudejie?catid=41&apikey=ERWbzCNCoCj7oRaVEJeNMFKz5VJiDzWLb6cQpsuMeyhPL3GQPn9fQbr9DuL16kam")
    fun getBaishiData(@Query("pageToken") pageToken: String? = "0"): Observable<DataVideoBean>

    @GET("/api/company/search_company?keyWord=上海")
    fun test(@Header("accessToken") accessToken: String): Observable<JSONObject>


    //-------------   通用----------------

    @GET("api/oss/get_oss_token")
    fun getOssDataBean(): Observable<BaseBean<DataVideoBean>>

    //------------   登录注册------------
    @POST("api/user/app_login")
    @FormUrlEncoded
    fun login(@Field("account") account: String, @Field("password") password: String, @Field("userAgent") userAgent: Int): Observable<ResponseBody>
}