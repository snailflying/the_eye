package com.theone.eye.ui.user.login

import com.themone.core.base.IModel
import com.theone.eye.ui.user.login.entity.LoginReq
import com.theone.eye.ui.user.login.entity.LoginRes
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface ILoginModel : IModel {

    /**
     * 登录
     *
     * @param params
     * @return
     */
    fun login(loginRes: LoginReq): Observable<ApiResponse<LoginRes>>
}