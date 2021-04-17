package com.theone.eye.ui.user.login

import com.themone.core.base.IModel
import com.theone.eye.ui.user.resetpwd.entity.ResetPwdReq
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IResetPwdModel:IModel {

    /**
     * 获取验证码
     *
     * @param params
     * @return
     */
    fun getVerifyCode(request: String): Observable<ApiResponse<Any>>

    /**
     * 重置密码
     *
     * @param params
     * @return
     */
    fun resetPwd(request: ResetPwdReq): Observable<ApiResponse<Any>>
}