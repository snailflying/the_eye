package com.theone.eye.base.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
@Parcelize
data class VerifyCodeReq(
    @SerializedName("phonenum", alternate = ["phoneNumber"])
    var phoneNumber: String? = null,
    var zoneCode: String? = "+86",
    var fieldType: VerifyCodeType = VerifyCodeType.LOGIN
) : Parcelable