package com.theone.eye.base.net.interceptor

import com.shownow.shownow.base.constant.Constant
import com.theone.eye.base.user.User
import com.theone.framework.base.BaseApp
import com.theone.framework.util.I18NUtil
import com.theone.framework.util.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * @Author zhiqiang
 * @Date 2019/2/28
 * @Description OkHttp拦截器:向请求头(Header)添加公共参数
 */
class HeaderInterceptor : Interceptor {

    private val headersMap = hashMapOf<String, String>()

    init {
        initCommonHeader()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        // 新的请求
        val requestBuilder = oldRequest.newBuilder()
        requestBuilder.method(
            oldRequest.method,
            oldRequest.body
        )
        //添加公共参数,添加到header中
        refreshHeader(requestBuilder)
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }

    private fun refreshHeader(builder: Request.Builder) {
        for (keySet in headersMap.keys) {
            builder.header(keySet, headersMap.getValue(keySet))
        }
        builder.header(Constant.ACCESS_TOKEN, User.currentUser.accessToken)
            .header(Constant.HEAD_LANGUAGE, I18NUtil.getSelectedLanguage())
            .header(Constant.HEAD_CURRENCY, I18NUtil.getSelectedCurrency())

        //添加登录cookie
        val cookieSet =
            SpUtil.getSp(BaseApp.application).getStringSet(SpUtil.SP_LOGIN_COOKIE, setOf()) as HashSet<String>
        for (cookie in cookieSet) {
            builder.addHeader("Cookie", cookie)
        }

    }

    private fun initCommonHeader() {
        headersMap[Constant.HEAD_PRODUCT] = Constant.HEAD_PRODUCT_VALUE
    }

}