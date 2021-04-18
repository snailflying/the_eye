package com.theone.eye.base.user

import android.annotation.SuppressLint
import com.theone.framework.base.BaseApp
import com.theone.framework.ext.*
import com.theone.framework.util.SpUtil

/**
 * @Author zhiqiang
 * @Date 2019-06-11
 * @Description User类的保存
 */
class Settings private constructor() {

    private val prefs by lazy { SpUtil.getSp(BaseApp.application) }

    var accessToken = prefs.getEncryptString(SP_ACCESS_TOKEN)
        set(value) {
            prefs.edit().putEncryptString(SP_ACCESS_TOKEN, value).apply()
            field = value
        }
    var userId = prefs.getEncryptInt(SP_USER_ID)
        set(value) {
            prefs.edit().putEncryptInt(SP_USER_ID, value).apply()
            field = value
        }
    var accessCookie = prefs.getStringSet(SP_ACCESS_COOKIE, emptySet())!!
        set(value) {
            prefs.edit().putStringSet(SP_ACCESS_COOKIE, value).apply()
            field = value
        }
    var sex = prefs.getEncryptInt(SP_SEX, DEFAULT_EMPTY_NUMBER)
        set(value) {
            prefs.edit().putEncryptInt(SP_SEX, value).apply()
            field = value
        }
    var avatarUrl = prefs.getEncryptString(SP_AVATAR_URL)
        set(value) {
            prefs.edit().putEncryptString(SP_AVATAR_URL, value).apply()
            field = value
        }
    var cellphone = prefs.getEncryptString(SP_MOBILE)
        set(value) {
            prefs.edit().putEncryptString(SP_MOBILE, value).apply()
            field = value
        }

    var name = prefs.getEncryptString(SP_NAME)
        set(value) {
            prefs.edit().putEncryptString(SP_NAME, value).apply()
            field = value
        }

    companion object {

        const val SP_ACCESS_TOKEN = "sp_access_token"
        const val SP_ACCESS_COOKIE = "sp_access_cookie"
        const val SP_USER_ID = "sp_user_id"
        const val SP_SEX = "sp_sex"
        const val SP_AVATAR_URL = "sp_avatar_url"
        const val SP_MOBILE = "sp_mobile"
        const val SP_NAME = "sp_name"
        const val DEFAULT_EMPTY_NUMBER = -1

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var defaultInstance: Settings? = null

        fun create(): Settings {
            if (defaultInstance == null) {
                synchronized(Settings::class.java) {
                    if (defaultInstance == null) {
                        defaultInstance = Settings()
                    }
                }
            }
            return defaultInstance!!
        }
    }
}