package com.theone.eye.base.net.interceptor

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.base.user.User
import com.theone.framework.base.BaseApp
import com.theone.framework.util.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * @Author ZhiQiang
 * @Date 2021/1/5
 * @Description 存储Cookie拦截器
 */
class CookieSaveInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse: Response = chain.proceed(request)
        if (isLoginRequestSuccess(request, originalResponse) && originalResponse.headers(COOKIE_KEY).isNotEmpty()) {
            val cookies = HashSet(originalResponse.headers(COOKIE_KEY))
            User.currentUser.accessCookie = cookies
        }
        return originalResponse
    }

    private fun isLoginRequestSuccess(request: Request, originalResponse: Response) =
        request.url.toString().contains(ApiService.LOGIN_PATH) && originalResponse.isSuccessful

    companion object {
        private const val COOKIE_KEY = "Set-Cookie"
    }

}