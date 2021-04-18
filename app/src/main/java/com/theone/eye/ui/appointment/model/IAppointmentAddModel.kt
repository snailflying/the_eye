package com.theone.eye.ui.appointment.model

import com.themone.core.base.IModel
import com.theone.eye.ui.appointment.entity.AppointmentAddReq
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IAppointmentAddModel : IModel {

    fun submitAppointment(req: AppointmentAddReq): Observable<ApiResponse<Any>>

}