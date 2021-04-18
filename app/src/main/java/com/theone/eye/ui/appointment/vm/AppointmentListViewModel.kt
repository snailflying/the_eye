package com.theone.eye.ui.home.vm

import androidx.lifecycle.MutableLiveData
import com.theone.eye.base.entity.HttpError
import com.theone.eye.ui.appointment.entity.AppointmentListRes
import com.theone.eye.ui.appointment.model.AppointmentListModel
import com.theone.eye.ui.appointment.model.IAppointmentListModel
import com.theone.framework.base.BaseViewModel

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class AppointmentListViewModel(override var model: IAppointmentListModel = AppointmentListModel()) :
    BaseViewModel<IAppointmentListModel>() {

    val appointmentListLive: MutableLiveData<List<AppointmentListRes>> = MutableLiveData()
    val httpErrorLive: MutableLiveData<HttpError> = MutableLiveData()

    fun getAppointment() {
        model.getAppointment()
            .subscribe(object : BaseObserver<List<AppointmentListRes>>() {
                override fun onResultSuccess(data: List<AppointmentListRes>?) {
                    if (data.isNullOrEmpty()) {
                        httpErrorLive.value = HttpError()
                    } else {
                        appointmentListLive.value = data
                    }
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    httpErrorLive.value = HttpError(statusCode, comments)
                }

                override fun onError(e: Throwable?) {
                    httpErrorLive.value = HttpError()
                }

            })
    }
}