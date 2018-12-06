package com.dragger2.reactnativetest1022.base.api.interceptor

import android.os.Handler
import android.os.Looper
import com.dragger2.reactnativetest1022.base.api.CodeError
import com.dragger2.reactnativetest1022.base.manager.ActivityStack
import com.dragger2.reactnativetest1022.base.thread.ThreadUtil
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

/**
 * 封号拦截
 * Created by wangru
 * Date: 2017/9/20  9:37
 * mail: 1902065822@qq.com
 * describe:
 */

class SealAccountInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        //请求前
        //        MyLogUtils.e(TAG, "url:" + request.url() + "  connection:" + chain.connection() + " headers:" + request.headers());
        //请求后
        val response = chain.proceed(request)

        if (response != null) {
            val responseBody = response.body()
            responseBody?.let {
                if (!bodyEncoded(response.headers())) {
                    val source = responseBody.source()
                    source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                    val buffer = source.buffer()

                    var charset: Charset? = UTF8
                    val contentType = responseBody.contentType()
                    if (contentType != null) {
                        try {
                            charset = contentType.charset(UTF8)
                        } catch (e: UnsupportedCharsetException) {
                            return response
                        }

                    }

                    if (!isPlaintext(buffer)) {
                        return response
                    }


                    val contentLength = responseBody.contentLength()
                    if (contentLength != 0L) {
                        val result = buffer.clone().readString(charset!!)

                        var json: JSONObject? = null
                        try {
                            json = JSONObject(result)
                            val code = json.optInt("code")

                            if (code == CodeError.TOKEN_OUTTIME) {
//                                Logger.d("intercept#token过期")
                                loginAgain()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }
                }

            }
        }
        return response
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers.get("Content-Encoding")
        return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
    }

    var dialog: DialogCommon? = null
    private fun loginAgain() {
        ThreadUtil.lopper {
            Handler(Looper.getMainLooper()).post {
                try {
                    ActivityStack.top()?.let {
                        if (dialog == null) {
                            dialog = DialogCommon(it, contentText = "登录过期,请重新登陆", onRightClick = {
//                                DataManager.clearUserData()
//                                DataManager.clearResumeData()
//                                ARouter.getInstance().build(RoutePath.LOGIN_LOG).navigation()
                            })
                        }
                        if (dialog?.isShowing?.not() ?: false) {
                            dialog?.show()
                        }
                    }
                } catch (e: Exception) {
                }
            }
        }
    }

    companion object {
        val TAG = SealAccountInterceptor::class.java.simpleName

        private val UTF8 = Charset.forName("UTF-8")

        @Throws(EOFException::class)
        internal fun isPlaintext(buffer: Buffer): Boolean {
            try {
                val prefix = Buffer()
                val byteCount = if (buffer.size() < 64) buffer.size() else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false
                    }
                }
                return true
            } catch (e: EOFException) {
                return false // Truncated UTF-8 sequence.
            }

        }
    }
}
