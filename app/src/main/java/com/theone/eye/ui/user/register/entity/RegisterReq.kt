package com.theone.eye.ui.user.register.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
@Parcelize
class RegisterReq(
    @SerializedName("loginname")
    var phoneNumber: String? = null,
    @SerializedName("name")
    var nickName: String? = null,
    @SerializedName("verifyCode")
    var smsVerifyCode: String? = null,
    var pwd: String? = null,
    var sex: String? = null,
    var zoneCode: String? = "+86"
) : Parcelable