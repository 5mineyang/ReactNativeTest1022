package com.dragger2.reactnativetest1022.base.api.interceptor


import android.util.Log
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

/**
 * 日志拦截
 * Created by wangru
 * mail: 1902065822@qq.com
 * describe:
 */

class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        //请求前
        try {
            val method = request.method();
            val sb = StringBuilder();
            if ("POST".equals(method)) {
                if (request.body() is FormBody) {
                    val body = request.body() as FormBody
                    for (i in 0..(body.size() - 1)) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length - 1, sb.length);
                }
            }
            val info = "Request{" + "method=[${request.method()}],url=[${request.url().toString()}]" + ", headers=[" + request.headers().toString() + "], isHttps=" + request.isHttps + ", Params=[${sb.toString()}]" + '}'
            Log.d(TAG, "intercept#request:\n$info")
        } catch (e: Exception) {
        }
        //请求后
        val response = chain.proceed(request)
        if (response != null) {
            val responseBody = response.body()
            val contentLength = responseBody!!.contentLength()

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
                if (contentLength != 0L) {
                    val result = buffer.clone().readString(charset!!)
//                    Logger.d("intercept#result:\n$result")
                }
            }
        }
        return response
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers.get("Content-Encoding")
        return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
    }

    companion object {
        val TAG = LogInterceptor::class.java.simpleName

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
