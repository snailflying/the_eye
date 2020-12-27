package com.theone.eye.base.net.interceptor

import com.theone.eye.base.net.api.ApiService
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author ZhiQiang
 * @Date 2020/10/16
 * @Description IP直连设置BaseUrl
 */
class MoreBaseUrlInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val ignoreChange = originalRequest.header(ApiService.IGNORE_CHANGE_URL)
        if (ignoreChange == ApiService.IGNORE_CHANGE_URL_YES) {
            return chain.proceed(originalRequest)
        }

        val oldUrl = originalRequest.url
        val builder = originalRequest.newBuilder()
        val baseURL: HttpUrl = ApiService.getBaseUrl().toHttpUrlOrNull()
            ?: return chain.proceed(originalRequest)
        //重建新的HttpUrl，需要重新设置的url部分
        val newHttpUrl = oldUrl.newBuilder()
            .scheme(baseURL.scheme) //http协议如：http或者https
            .host(baseURL.host) //主机地址
            .port(baseURL.port) //端口
            .build()
        val newRequest = builder.url(newHttpUrl).build()
        return chain.proceed(newRequest)
    }
}