package com.theone.eye.base.entity

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
    var userId: String? = null,
    var appointmentTime: String? = null,
    var appointPhone: String? = null,
    var appointAddr: String? = null,
    var appointName: String? = null,
    var userAge: String? = null,
    /**
     * 处理状态 0 未处理 1 已处理
     */
    var status: String? = null
) : Parcelable