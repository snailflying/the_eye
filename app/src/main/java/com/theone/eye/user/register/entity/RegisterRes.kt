package com.theone.eye.user.register.entity

import java.io.Serializable

class RegisterRes : Serializable {
    var token: String? = null
    var refreshToken: String? = null

    var userId: String? = null
    var name: String? = null
    var cellphone: String? = null
    var email: String? = null

    var errorMsg: String? = null

}