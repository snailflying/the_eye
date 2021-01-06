package com.theone.eye.base.user

import com.theone.eye.user.login.entity.LoginRes
import com.theone.framework.http.HttpClient
import java.io.Serializable

/**
 * @Author zhiqiang
 * @Date 2019-06-11
 * @Description
 */
class User private constructor() : Serializable {

    var accessToken: String = ""
        get() = if (field.isNotBlank()) field else Settings.create().accessToken
        set(value) {
            Settings.create().accessToken = value
            field = value
        }
    var accessCookie: Set<String> = emptySet()
        get() = if (field.isNotEmpty()) field else Settings.create().accessCookie
        set(value) {
            Settings.create().accessCookie = value
            field = value
        }
    var sex: Int = Settings.DEFAULT_SEX
        get() = if (field != Settings.DEFAULT_SEX) field else Settings.create().sex
        set(value) {
            Settings.create().sex = value
            field = value
        }
    var avatarUrl: String = ""
        get() = if (field.isNotBlank()) field else Settings.create().avatarUrl
        set(value) {
            Settings.create().avatarUrl = value
            field = value
        }
    var cellphone: String = ""
        get() = if (field.isNotBlank()) field else Settings.create().cellphone
        set(value) {
            Settings.create().cellphone = value
            field = value
        }


    var name: String = ""
        get() = if (field.isNotBlank()) field else Settings.create().name
        set(value) {
            Settings.create().name = value
            field = value
        }


    init {
        if (Settings.create().accessToken.isNotBlank())
            this.accessToken = Settings.create().accessToken

        if (!Settings.create().accessCookie.isNullOrEmpty())
            this.accessCookie = Settings.create().accessCookie

        if (Settings.create().sex != Settings.DEFAULT_SEX)
            this.sex = Settings.create().sex
        if (Settings.create().avatarUrl.isNotBlank())
            this.avatarUrl = Settings.create().avatarUrl

        if (Settings.create().cellphone.isNotBlank())
            this.cellphone = Settings.create().cellphone

        if (Settings.create().name.isNotBlank())
            this.name = Settings.create().name
    }

    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean = accessCookie.isNotEmpty()

    /**
     * 登录
     * @param userLogin LoginEn
     */
    fun login(userLogin: LoginRes) {
        //init
        this.name = userLogin.name ?: ""
        this.avatarUrl = userLogin.imgPath ?: ""
        this.sex = userLogin.sex ?: Settings.DEFAULT_SEX
        currentUser = this@User
    }

    /**
     * 退出登录
     */
    fun logout() {
        accessToken = ""
        accessCookie = emptySet()
        sex = Settings.DEFAULT_SEX
        cellphone = ""
        avatarUrl = ""
        name = ""
        currentUser = User()

        //退出登录时删除网络缓存
        HttpClient.okHttpClient.cache?.evictAll()
    }

    /**
     * {@kotlin User.currentUser.isLogin()}
     * {@java User.Companion.getCurrentUser().isLogin();}
     */
    companion object {
        var currentUser: User = User()
    }


}