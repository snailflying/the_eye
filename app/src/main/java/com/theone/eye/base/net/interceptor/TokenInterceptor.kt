package com.theone.eye.base.net.interceptor

import com.shownow.shownow.base.constant.HttpStatusCode
import com.themone.core.util.LogUtil
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.base.user.User
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

/**
 * @Author zhiqiang
 * @Email liuzhiqiang@piaoyou.com
 * @Date 2019/2/28
 * @Description OkHttp拦截器:监听异常事件,注意：此类会将body全部加载到内存。大文件下载时不要开启
 */
class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        try {
            //拦截了响应体
            val responseBody = response.body
            val result = getResponseString(responseBody)
            val statusCode = getResponseStatusCode(result)

            //isRefreshRequest() 判断刷新token连接是否是本身，避免递归死循环。
            val needProceedRefreshToken = (statusCode == HttpStatusCode.LOGIN_EXPIRED) && !isRefreshRequest(request)
            if (statusCode == HttpStatusCode.LOGIN_EXPIRED) {
                User.currentUser.logout()
            }
        } catch (e: Exception) {
            LogUtil.e(TAG, e.message)
        }
        return response
    }

    private fun isRefreshRequest(request: Request): Boolean {
        val url = request.url.toString()
        val refreshUrlPath = ApiService.USER_REFRESH_TOKEN
        return url.contains(refreshUrlPath)
    }


    @Throws(IOException::class)
    private fun getResponseString(responseBody: ResponseBody?): String {
        if (responseBody != null) {
            val source = responseBody.source()
            // Buffer the entire body.
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            return buffer.clone().readString(UTF8)
        }
        return ""
    }

    /**
     *
     * @param bodyString
     * @return
     * @throws JSONException
     */
    @Throws(JSONException::class)
    private fun getResponseStatusCode(bodyString: String): Int {

        if (bodyString.startsWith(JSON_START)) {
            val json = JSONObject(bodyString)
            return json.optInt(STATUS_CODE)
        }
        return HttpStatusCode.SUCCESS
    }

    companion object {
        private const val TAG = "TokenInterceptor"
        private const val JSON_START = "{"
        private const val STATUS_CODE = "statusCode"
        private val UTF8 = Charset.forName("UTF-8")
    }
}