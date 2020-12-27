package com.theone.eye.user.login

import com.themone.core.base.IModel
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IRegisterModel : IModel {
    /**
     * 获取验证码
     *
     * @param params
     * @return
     */
    fun getVerifyCode(request: VerifyCodeReq): Observable<ApiResponse<Any>>

    /**
     * 注册
     *
     * @param params
     * @return
     */
    fun register(request: RegisterReq): Observable<ApiResponse<RegisterRes>>
}