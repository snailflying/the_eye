package com.theone.framework.base

import android.content.Context
import androidx.annotation.IdRes
import com.themone.core.base.impl.CoreActivity
import com.themone.core.base.impl.CoreFragment
import com.theone.framework.R
import com.theone.framework.util.I18NUtil

/**
 * @Author zhiqiang
 * @Date 2019-06-19
 * @Email liuzhiqiang@theone.com
 * @Description
 */
open class BaseActivity : CoreActivity(), IFrameworkActivity {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(I18NUtil.updateResource(newBase))
    }

}