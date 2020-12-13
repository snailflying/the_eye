package com.theone.eye.user.login

import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.themone.core.base.IModel
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
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
    fun getVerifyCode(request: VerifyCodeReq): Observable<ApiResponse<Boolean>>

    /**
     * 重置密码
     *
     * @param params
     * @return
     */
    fun resetPwd(request: ResetPwdReq): Observable<ApiResponse<Boolean>>
}