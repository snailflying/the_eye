package com.theone.eye.ui.appointment.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
@Parcelize
data class AppointmentListRes(
    var id: String? = null,
    var userId: Int? = null,
    var appointmentTime: String? = null,
    var appointPhone: String? = null,
    var appointAddr: String? = null,
    var appointName: String? = null,
    var sex: String? = null,
    var userAge: String? = null,
    /**
     * 处理状态 0 未处理 1 已处理
     */
    var status: Int? = null
) : Parcelable {
    fun isStatusDone(): Boolean {
        return status == 1
    }
}