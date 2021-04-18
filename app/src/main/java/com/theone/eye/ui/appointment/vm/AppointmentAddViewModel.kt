package com.theone.eye.ui.home.vm

import androidx.lifecycle.MutableLiveData
import com.theone.eye.R
import com.theone.eye.ui.appointment.entity.AppointmentAddReq
import com.theone.eye.ui.appointment.model.AppointmentAddModel
import com.theone.eye.ui.appointment.model.IAppointmentAddModel
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString
import com.theone.framework.widget.toast.ToastUtil

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class AppointmentAddViewModel(override var model: IAppointmentAddModel = AppointmentAddModel()) :
    BaseViewModel<IAppointmentAddModel>() {

    val resultData = MutableLiveData<Boolean>()

    fun submitAppointment(appointmentReq: AppointmentAddReq) {
        if (!canSubmit(appointmentReq)) return

        model.submitAppointment(appointmentReq)
            .subscribe(object : BaseObserver<Any>() {
                override fun onResultSuccess(data: Any?) {
                    resultData.postValue(true)
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    resultData.postValue(false)
                }

                override fun onError(e: Throwable?) {
                    resultData.postValue(false)
                }

            })
    }

    private fun canSubmit(appointmentReq: AppointmentAddReq): Boolean {
        if (appointmentReq.appointName.isNullOrEmpty()) {
            ToastUtil.show(getString(R.string.need_name))
            return false
        }
        if (appointmentReq.userAge == null) {
            ToastUtil.show(getString(R.string.need_age))
            return false
        }
        if (appointmentReq.appointPhone == null) {
            ToastUtil.show(getString(R.string.need_phone))
            return false
        }
        if (appointmentReq.appointAddr == null) {
            ToastUtil.show(getString(R.string.need_address))
            return false
        }
        if (appointmentReq.appointmentTime == null) {
            ToastUtil.show(getString(R.string.need_appointment_time))
            return false
        }
        return true
    }

}