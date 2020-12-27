package com.theone.eye.base.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
class VerifyCodeReq : Serializable {
    @SerializedName("phonenum", alternate = ["phoneNumber"])
    var phoneNumber: String? = null
    var zoneCode: String? = "+86"
    var fieldType: VerifyCodeType = VerifyCodeType.LOGIN
}