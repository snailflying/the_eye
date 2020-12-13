package com.theone.eye.user.login

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.theone.eye.R
import com.theone.eye.base.entity.VerifyCodeReq
import com.theone.eye.home.adapter.HomeBannerBinder
import com.theone.eye.user.register.entity.RegisterReq
import com.theone.eye.user.register.entity.RegisterRes
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class RegisterViewModel(override var model: IRegisterModel = RegisterModel()) : BaseViewModel<IRegisterModel>() {
    private val items = CopyOnWriteArrayList<Any>()
    private val mHomeMultiAdapter: MultiTypeAdapter = MultiTypeAdapter(items)
    val verifyLive: MutableLiveData<Boolean> = MutableLiveData()
    val registerLive: MutableLiveData<RegisterRes> = MutableLiveData()

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
                    verifyLive.value = data ?: false
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
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