package com.theone.eye.user.login.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginReq : Serializable {
    var phoneNumber: String? = null
    var pwd: String? = null
}