package com.theone.eye.user.register.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
class RegisterReq : Serializable {
    @SerializedName("loginname")
    var phoneNumber: String? = null
    @SerializedName("name")
    var nickName: String? = null
    @SerializedName("verifyCode")
    var smsVerifyCode: String? = null
    var pwd: String? = null
    var sex: String? = null
    var zoneCode: String? = "+86"
}