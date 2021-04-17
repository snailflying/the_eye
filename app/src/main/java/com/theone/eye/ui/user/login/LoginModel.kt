package com.theone.eye.ui.user.login

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.user.login.entity.LoginReq
import com.theone.eye.ui.user.login.entity.LoginRes
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class LoginModel : BaseModel<ApiService>(ApiService::class.java), ILoginModel {


    override fun login(loginRes: LoginReq): Observable<ApiResponse<LoginRes>> {
        return apiService.login(loginRes.phoneNumber,loginRes.pwd).compose(RxUtil.toMainThread())
    }

}