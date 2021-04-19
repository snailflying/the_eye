package com.theone.eye.base

import com.chenenyu.router.RouteInterceptor
import com.chenenyu.router.RouteResponse
import com.chenenyu.router.Router
import com.chenenyu.router.annotation.Interceptor
import com.theone.eye.base.user.User
import com.theone.framework.router.AppRouteUrl

/**
 * @Author zhiqiang
 * @Date 2019/2/20
 * @Email liuzhiqiang@piaoyou.com
 * @Description
 */
@Interceptor(AppRouteUrl.LOGIN_ROUTE_INTERCEPTOR)
class RouteLoginInterceptor : RouteInterceptor {
    override fun intercept(chain: RouteInterceptor.Chain): RouteResponse {
        return if (User.currentUser.isLogin()) {
            chain.process()
        } else { //如果需要登录页面返回时做相应处理
            var requestCode = chain.request.requestCode
            if (requestCode < 0) {
                requestCode = chain.request.extras.getInt(AppRouteUrl.LOGIN_REQUEST_CODE, -1)
            }
            Router.build(AppRouteUrl.LOGIN_URL).requestCode(requestCode).go(chain.context)
            chain.intercept()
        }
    }
}