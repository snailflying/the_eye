package com.theone.eye.user.resetpwd.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
class ResetPwdReq : Serializable {
    @SerializedName("loginname")
    var phoneNumber: String? = null

    @SerializedName("verifyCode")
    var smsVerifyCode: String? = null
    var newPwd: String? = null
    var zoneCode: String? = "+86"
}