package com.theone.eye.user.login

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.theone.eye.R
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.home.adapter.HomeBannerBinder
import com.theone.eye.user.resetpwd.entity.ResetPwdReq
import com.theone.eye.user.resetpwd.entity.ResetPwdRes
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ResetPwdViewModel(override var model: IResetPwdModel = ResetPwdModel()) : BaseViewModel<IResetPwdModel>() {
    private val items = CopyOnWriteArrayList<Any>()
    private val mHomeMultiAdapter: MultiTypeAdapter = MultiTypeAdapter(items)
    val resetPwdLive: MutableLiveData<ResetPwdRes> = MutableLiveData()

    fun initRecyclerView(recyclerView: RecyclerView) {
        //Banner
        /**
         * Banner切换时的回调
         */
        val homeBannerBinder = HomeBannerBinder()

        mHomeMultiAdapter.register(homeBannerBinder)

        recyclerView.adapter = mHomeMultiAdapter
    }


    fun getVerifyCode(verifyCodeReq: VerifyCodeReq) {
        model.getVerifyCode(verifyCodeReq)
            .subscribe(object : BaseObserver<Boolean>() {
                override fun onResultSuccess(data: Boolean?) {
                    TODO("Not yet implemented")
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun resetPassword(resetPwdReq: ResetPwdReq) {
        model.resetPwd(resetPwdReq)
            .subscribe(object : BaseObserver<Boolean>() {
                override fun onResultSuccess(data: Boolean?) {
                    if (data == true) {
                        resetPwdLive.value = ResetPwdRes().also {
                            it.result = data
                        }
                    } else {
                        resetPwdLive.value = ResetPwdRes().also {
                            it.result = false
                            it.errorMsg = getString(R.string.reset_pwd_error)
                        }
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