package com.theone.eye.ui.appointment.model

import com.themone.core.base.IModel
import com.theone.eye.ui.appointment.entity.AppointmentListRes
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IAppointmentListModel:IModel {

    fun getAppointment(): Observable<ApiResponse<List<AppointmentListRes>>>

}