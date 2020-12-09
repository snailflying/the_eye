package com.theone.eye.user.login

import android.os.Bundle
import cn.magicwindow.core.ext.toFragment
import com.theone.eye.R
import com.theone.framework.base.BaseActivity

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        toFragment(R.id.container_fragment, RegisterFragment.getInstance())
    }
}