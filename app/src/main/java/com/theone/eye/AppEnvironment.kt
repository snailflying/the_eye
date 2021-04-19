package com.theone.eye

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.base.net.interceptor.*
import com.theone.framework.http.DefaultEnvironment
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * @Author zhiqiang
 * @Date 2019-06-11
 * @Description
 */
class AppEnvironment : DefaultEnvironment() {


    companion object {
        const val FLAVOR_DEV = "dev"
        const val FLAVOR_QA = "qa"
        const val FLAVOR_ONLINE = "online"
        const val FLAVOR_GOOGLE = "google"
        val productFlavor: String
            get() = BuildConfig.PRODUCT_FLAVORS
    }

    override val apiBaseServiceUrl: String
        get() {
            return when (productFlavor) {
                FLAVOR_ONLINE, FLAVOR_GOOGLE -> ApiService.BASE_URL_ONLINE
                FLAVOR_DEV -> ApiService.BASE_URL_DEV
                FLAVOR_QA -> ApiService.BASE_URL_QA
                else -> ApiService.BASE_URL_ONLINE
            }
        }
    override val okHttpClient: OkHttpClient
        get() {
            val builder = super.okHttpClient.newBuilder()
            builder.authenticator(TokenAuthenticator())
            return builder.build()
        }
    override val interceptors: MutableList<Interceptor>
        get() {
            val interceptors = super.interceptors
            val headerInterceptor = HeaderInterceptor()
            val cookieSaveInterceptor = CookieSaveInterceptor()

            val moreBaseUrlInterceptor = MoreBaseUrlInterceptor()
            interceptors.add(headerInterceptor)
            interceptors.add(cookieSaveInterceptor)
            interceptors.add(moreBaseUrlInterceptor)
            interceptors.add(TokenInterceptor())
            return interceptors
        }


}
