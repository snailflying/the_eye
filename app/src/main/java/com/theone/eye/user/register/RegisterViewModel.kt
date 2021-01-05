package com.theone.eye.user.login

import androidx.lifecycle.MutableLiveData
import com.theone.eye.R
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class RegisterViewModel(override var model: IRegisterModel = RegisterModel()) : BaseViewModel<IRegisterModel>() {
    val verifyLive: MutableLiveData<Boolean> = MutableLiveData()
    val registerLive: MutableLiveData<RegisterRes> = MutableLiveData()

    fun getVerifyCode(verifyCodeReq: String) {
        model.getVerifyCode(verifyCodeReq)
            .subscribe(object : BaseObserver<Any>() {
                override fun onResultSuccess(data: Any?) {
                    verifyLive.value = true
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    verifyLive.value = false

                }

                override fun onError(e: Throwable?) {
                    verifyLive.value = false
                }

            })
    }

    fun register(registerReq: RegisterReq) {
        model.register(registerReq)
            .subscribe(object : BaseObserver<RegisterRes>() {
                override fun onResultSuccess(data: RegisterRes?) {
                    registerLive.value = data
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    registerLive.value =
                        RegisterRes().also { it.errorMsg = comments ?: getString(R.string.regist_error) }

                }

                override fun onError(e: Throwable?) {
                    registerLive.value = RegisterRes().also { it.errorMsg = getString(R.string.regist_error) }
                }

            })
    }
}