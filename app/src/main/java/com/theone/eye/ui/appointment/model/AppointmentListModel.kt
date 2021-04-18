package com.theone.eye.ui.appointment.model

import com.theone.eye.ui.appointment.entity.AppointmentListRes
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class AppointmentListModel : BaseModel<ApiService>(ApiService::class.java), IAppointmentListModel {


    companion object {

        /**
         * 首页banner图片地址
         */
        const val HOME_BANNER = "https://static1.piaoxingqiu.cn/PXQ/assets/img/app/home_banner.png"
    }

    override fun getAppointment(): Observable<ApiResponse<List<AppointmentListRes>>> {
        return apiService.getAppointment().compose(RxUtil.toMainThread())
    }

}