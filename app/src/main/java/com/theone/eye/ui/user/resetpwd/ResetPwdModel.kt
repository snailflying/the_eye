package com.theone.eye.ui.user.login

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.user.resetpwd.entity.ResetPwdReq
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ResetPwdModel : BaseModel<ApiService>(ApiService::class.java), IResetPwdModel {
    override fun getVerifyCode(request: String): Observable<ApiResponse<Any>> {
        return apiService.getVerifyCode(request).compose(RxUtil.toMainThread())
    }

    override fun resetPwd(request: ResetPwdReq): Observable<ApiResponse<Any>> {
        return apiService.resetPassword(request.smsVerifyCode, request.phoneNumber, request.newPwd)
            .compose(RxUtil.toMainThread())
    }


}