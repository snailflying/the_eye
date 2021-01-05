package com.theone.eye.user.login

import androidx.lifecycle.MutableLiveData
import com.theone.eye.R
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
import com.theone.eye.user.resetpwd.entity.ResetPwdRes
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ResetPwdViewModel(override var model: IResetPwdModel = ResetPwdModel()) : BaseViewModel<IResetPwdModel>() {
    val verifyLive: MutableLiveData<Boolean> = MutableLiveData()
    val resetPwdLive: MutableLiveData<ResetPwdRes> = MutableLiveData()


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

    fun resetPassword(resetPwdReq: ResetPwdReq) {
        model.resetPwd(resetPwdReq)
            .subscribe(object : BaseObserver<Any>() {
                override fun onResultSuccess(data: Any?) {
                    resetPwdLive.value = ResetPwdRes().also {
                        it.result = true
                    }

                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    resetPwdLive.value = ResetPwdRes().also {
                        it.result = false
                        it.errorMsg = comments ?: getString(R.string.reset_pwd_error)
                    }

                }

                override fun onError(e: Throwable?) {
                    resetPwdLive.value = ResetPwdRes().also {
                        it.result = false
                        it.errorMsg = getString(R.string.reset_pwd_error)
                    }
                }

            })
    }
}