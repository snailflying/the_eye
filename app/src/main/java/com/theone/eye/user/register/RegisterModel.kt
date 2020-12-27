package com.theone.eye.user.login

import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.piaoyou.piaoxingqiu.app.entity.api.FloorItem
import com.piaoyou.piaoxingqiu.app.entity.api.FloorRoom
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.home.entity.FloorTypeConstants
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
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
    override fun getVerifyCode(request: VerifyCodeReq): Observable<ApiResponse<Any>> {
        return apiService.getVerifyCode(request).compose(RxUtil.toMainThread())
    }

    override fun register(request: RegisterReq): Observable<ApiResponse<RegisterRes>> {
        return apiService.register(request).compose(RxUtil.toMainThread())
    }


}