package com.theone.eye.user.login

import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.piaoyou.piaoxingqiu.app.entity.api.FloorItem
import com.piaoyou.piaoxingqiu.app.entity.api.FloorRoom
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.home.entity.FloorTypeConstants
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
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
    override fun getVerifyCode(request: VerifyCodeReq): Observable<ApiResponse<Boolean>> {
        return apiService.getVerifyCode(request).compose(RxUtil.toMainThread())
    }

    override fun resetPwd(request: ResetPwdReq): Observable<ApiResponse<Boolean>> {
        return apiService.resetPassword(request).compose(RxUtil.toMainThread())
    }


}