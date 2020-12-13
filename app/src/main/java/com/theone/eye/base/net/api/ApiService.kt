package com.theone.eye.base.net.api

import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.home.entity.HomeEn
import com.theone.eye.user.login.entity.LoginReq
import com.theone.eye.user.login.entity.LoginRes
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Author zhiqiang
 * @Date 2019-06-19
 * @Description
 */
interface ApiService {

    /**
     * 全路径
     * @return Observable<HomeEn>
     */
    @GET("https://api.mock.live/mock_android.json")
    fun getMock(): Observable<HomeEn>

    /**
     * 获取验证码
     */
    @POST("pub/get_verify_code")
    fun getVerifyCode(@Body request: VerifyCodeReq): Observable<ApiResponse<Boolean>>

    /**
     * 登录
     */
    @POST("pub/login")
    fun login(@Body request: LoginReq): Observable<ApiResponse<LoginRes>>

    /**
     * 注册
     */
    @POST("pub/register")
    fun register(@Body request: RegisterReq): Observable<ApiResponse<RegisterRes>>

    /**
     * 重置密码
     */
    @POST("pub/reset_password")
    fun resetPassword(@Body request: ResetPwdReq): Observable<ApiResponse<Boolean>>

    companion object {

        /**
         * 最后一个字符必须带"/"
         */
        const val BASE_URL_ONLINE = "https://appapi.shownow.live/shownowapi/"
        const val BASE_URL_QA = "https://appapiqa666.shownow.live/shownowapi/"
        const val BASE_URL_DEV = "https://appapidev999.shownow.live/shownowapi/"

        const val BASE_H5_URL_ONLINE = "https://m.shownow.live/"
        const val BASE_H5_URL_QA = "https://mqa666.shownow.live/"
        const val BASE_H5_URL_DEV = "https://mdev999.shownow.live/"


        const val HEADER_NO_CACHE = "no-cache, no-store, "
        const val VERSION_UPDATE = "http://7xk0r4.dl1.z0.glb.clouddn.com/version.json"
    }

}
