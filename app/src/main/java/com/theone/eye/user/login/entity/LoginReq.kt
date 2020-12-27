package com.theone.eye.user.login.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginReq : Serializable {
    @SerializedName("loginname")
    var phoneNumber: String? = null
    var pwd: String? = null
}