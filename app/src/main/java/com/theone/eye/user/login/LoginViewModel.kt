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
    val loginLive: MutableLiveData<LoginRes> = MutableLiveData()

    fun loginByVerifyCode(phoneNumber: String?, password: String?) {
        val request = LoginReq().also {
            it.telphone = phoneNumber
            it.pwd = password
        }
        model.login(request)
            .subscribe(object : BaseObserver<LoginRes>() {
                override fun onResultSuccess(data: LoginRes?) {
                    if (data == null) return
                    loginLive.value = data
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

            })
    }
}