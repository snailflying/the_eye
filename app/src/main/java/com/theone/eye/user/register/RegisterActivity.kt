package com.theone.eye.user.login

import android.os.Bundle
import cn.magicwindow.core.ext.toFragment
import com.chenenyu.router.annotation.Route
import com.theone.eye.R
import com.theone.framework.base.BaseActivity
import com.theone.framework.router.AppRouteUrl

@Route(value = [AppRouteUrl.REGISTER_URL])
class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        toFragment(R.id.container_fragment, RegisterFragment.getInstance())
    }
}