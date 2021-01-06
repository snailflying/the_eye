package com.theone.eye.user.login.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginRes : Serializable {

    var name: String? = null
    var sex: Int? = null
    @SerializedName("imgpath")
    var imgPath: String? = null

}