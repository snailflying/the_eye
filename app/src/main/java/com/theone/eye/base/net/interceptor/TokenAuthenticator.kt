package com.theone.eye.base.net.interceptor

import com.themone.core.util.LogUtil
import com.theone.eye.base.user.User
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TokenAuthenticator : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        LogUtil.e("logout")
        User.currentUser.logout()
        return response.request.newBuilder() //                .header("token", newToken)
            .build()
    }
}