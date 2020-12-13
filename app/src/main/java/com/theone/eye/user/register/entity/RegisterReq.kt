package com.theone.eye.user.register.entity

import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */
class RegisterReq : Serializable {

    var phoneNumber: String? = null
    var nickName: String? = null
    var smsVerifyCode: String? = null
    var pwd: String? = null
    var zoneCode: String? = "+86"
}