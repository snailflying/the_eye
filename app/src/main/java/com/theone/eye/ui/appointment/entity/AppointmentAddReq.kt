package com.theone.eye.ui.appointment.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
@Parcelize
data class AppointmentAddReq(
    var userId: Int? = null,
    var appointmentTime: String? = null,
    var appointPhone: String? = null,
    var appointAddr: String? = null,
    var appointName: String? = null,
    var userAge: Int? = null,
    /**
     * 处理状态 0 未处理 1 已处理
     */
    var status: String? = null
) : Parcelable {
    fun setName(name: String?) {
        appointName = name
    }

    fun setAge(age: Int?) {
        userAge = age
    }

    fun setTime(time: String?) {
        appointmentTime = time
    }

    fun setPhone(phone: String?) {
        appointPhone = phone
    }

    fun setAddresss(address: String?) {
        appointAddr = address
    }
}