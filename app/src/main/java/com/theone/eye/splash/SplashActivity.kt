package com.theone.eye.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.theone.eye.R
import com.theone.framework.base.BaseActivity
import com.theone.framework.router.AppRouteUrl
import com.theone.framework.router.AppRouter

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        toHome()
    }

    private fun toHome() {
        AppRouter.build(AppRouteUrl.ROUTE_HOME_URL).go(this)
    }
}