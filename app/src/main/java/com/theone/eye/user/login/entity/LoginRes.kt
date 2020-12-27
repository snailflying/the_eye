package com.theone.eye.user.login.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginRes : Serializable {
    var token: String? = null
    var refreshToken: String? = null

    var name: String? = null
    var phoneNumber: String? = null
    var sex: String? = null
    @SerializedName("imgpath")
    var imgPath: String? = null

}