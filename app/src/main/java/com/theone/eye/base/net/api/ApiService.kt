package com.theone.eye.base.net.api

import com.shownow.shownow.base.constant.NetConstant
import com.theone.eye.ui.home.entity.HomeEn
import com.theone.eye.ui.user.login.entity.LoginRes
import com.theone.eye.ui.user.register.entity.RegisterRes
import com.theone.framework.http.ApiResponse
import com.theone.framework.http.EnvironmentManager
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

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
    @FormUrlEncoded
    @POST("user/getVerifyCode")
    fun getVerifyCode(@Field(NetConstant.NET_PHONE_NUM) phonenum: String): Observable<ApiResponse<Any>>

    /**
     * 登录
     */
    @GET(LOGIN_PATH)
    fun login(
        @Query(NetConstant.NET_LOGIN_NAME) loginName: String?,
        @Query(NetConstant.NET_PWD) pwd: String?
    ): Observable<ApiResponse<LoginRes>>

    /**
     * 注册
     */
    @GET("user/addUser")
    fun register(
        @Query(NetConstant.NET_VERIFY_CODE) verifyCode: String?,
        @Query(NetConstant.NET_LOGIN_NAME) loginName: String?,
        @Query(NetConstant.NET_PWD) pwd: String?,
        @Query(NetConstant.NET_NAME) name: String?
    ): Observable<ApiResponse<RegisterRes>>

    /**
     * 重置密码
     */
    @GET("user/changeUserPwd")
    fun resetPassword(
        @Query(NetConstant.NET_VERIFY_CODE) name: String?,
        @Query(NetConstant.NET_LOGIN_NAME) loginName: String?,
        @Query(NetConstant.NET_NEW_PWD) pwd: String?
    ): Observable<ApiResponse<Any>>

    /**
     * 获取预约列表
     */
    @GET("appointment/getMyAppointmentList")
    fun getAppointment(
        @Query(NetConstant.NET_VERIFY_CODE) name: String?,
        @Query(NetConstant.NET_LOGIN_NAME) loginName: String?,
        @Query(NetConstant.NET_NEW_PWD) pwd: String?
    ): Observable<ApiResponse<Any>>

    companion object {

        /**
         * 最后一个字符必须带"/"
         */
        const val BASE_URL_ONLINE = "http://47.101.151.99:8090/"
        const val BASE_URL_QA = "http://47.101.151.99:8090/"
        const val BASE_URL_DEV = "http://47.101.151.99:8090/"

        const val BASE_H5_URL_ONLINE = "https://m.shownow.live/"
        const val BASE_H5_URL_QA = "https://mqa666.shownow.live/"
        const val BASE_H5_URL_DEV = "https://mdev999.shownow.live/"


        const val HEADER_NO_CACHE = "no-cache, no-store, "
        const val VERSION_UPDATE = "http://7xk0r4.dl1.z0.glb.clouddn.com/version.json"


        const val IGNORE_CHANGE_URL = "url_no_change"
        const val IGNORE_CHANGE_URL_YES = "yes"
        const val LOGIN_PATH = "login/login"

        fun getBaseUrl() = EnvironmentManager.environment.apiBaseServiceUrl
    }

}
