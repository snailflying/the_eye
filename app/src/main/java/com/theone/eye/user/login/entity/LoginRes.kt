package com.theone.eye.user.login.entity

import java.io.Serializable

class LoginRes : Serializable {
    var token: String? = null
    var refreshToken: String? = null

    var name: String? = null
    var phoneNumber: String? = null
    var email: String? = null

}