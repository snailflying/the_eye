package com.theone.eye.ui.appointment.model

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.appointment.entity.AppointmentAddReq
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 4/18/21
 * @Description
 */
class AppointmentAddModel : BaseModel<ApiService>(ApiService::class.java), IAppointmentAddModel {
    override fun submitAppointment(req: AppointmentAddReq): Observable<ApiResponse<Any>> {
        return apiService.addAppointment(
            req.appointPhone,
            req.appointmentTime,
            req.appointAddr,
            req.appointName,
            req.userAge,
            req.userId
        )
            .compose(RxUtil.toMainThread())
    }
}