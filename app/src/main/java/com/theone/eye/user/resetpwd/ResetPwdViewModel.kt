package com.theone.eye.user.login

import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.theone.eye.home.adapter.HomeBannerBinder
import com.theone.eye.home.model.HomeModel
import com.theone.eye.home.model.IHomeModel
import com.theone.framework.base.BaseViewModel
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ResetPwdViewModel(override var model: IResetPwdModel = ResetPwdModel()) : BaseViewModel<IResetPwdModel>() {
    private val items = CopyOnWriteArrayList<Any>()
    private val mHomeMultiAdapter: MultiTypeAdapter = MultiTypeAdapter(items)

    fun initRecyclerView(recyclerView: RecyclerView) {
        //Banner
        /**
         * Banner切换时的回调
         */
        val homeBannerBinder = HomeBannerBinder()

        mHomeMultiAdapter.register(homeBannerBinder)

        recyclerView.adapter = mHomeMultiAdapter
    }

    fun getData() {
        model.getHomeFloor()
            .subscribe(object : BaseObserver<FloorBean>() {
                override fun onResultSuccess(data: FloorBean?) {
                    if (data == null) return
                    items.add(HomeBannerBinder.HomeBannerEn(data))
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