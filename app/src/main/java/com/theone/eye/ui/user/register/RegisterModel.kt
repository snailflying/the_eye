package com.theone.eye.ui.user.login

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.user.register.entity.RegisterReq
import com.theone.eye.ui.user.register.entity.RegisterRes
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class RegisterModel : BaseModel<ApiService>(ApiService::class.java), IRegisterModel {
    override fun getVerifyCode(request: String): Observable<ApiResponse<Any>> {
        return apiService.getVerifyCode(request).compose(RxUtil.toMainThread())
    }

    override fun register(request: RegisterReq): Observable<ApiResponse<RegisterRes>> {
        return apiService.register(request.smsVerifyCode,request.phoneNumber,request.pwd,request.nickName).compose(RxUtil.toMainThread())
    }


}