package com.theone.eye.user.login

import androidx.lifecycle.MutableLiveData
import com.theone.eye.user.login.entity.LoginReq
import com.theone.eye.user.login.entity.LoginRes
import com.theone.framework.base.BaseViewModel

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class LoginViewModel(override var model: ILoginModel = LoginModel()) : BaseViewModel<ILoginModel>() {
    val loginLive: MutableLiveData<LoginRes?> = MutableLiveData()

    fun loginByVerifyCode(phoneNumber: String?, password: String?) {
        val request = LoginReq().also {
            it.phoneNumber = phoneNumber
            it.pwd = password
        }
        model.login(request)
            .subscribe(object : BaseObserver<LoginRes>() {
                override fun onResultSuccess(data: LoginRes?) {
                    loginLive.value = data
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    loginLive.value = null
                }

                override fun onError(e: Throwable?) {
                    loginLive.value = null
                }

            })
    }
}