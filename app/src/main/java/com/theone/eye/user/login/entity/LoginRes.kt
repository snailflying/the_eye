package com.theone.eye.user.login.entity

import java.io.Serializable

class LoginRes : Serializable {
    var token: String? = null
    var refreshToken: String? = null

    var userId: String? = null
    var name: String? = null
    var cellphone: String? = null
    var email: String? = null

}