package com.theone.eye

import android.content.Context
import androidx.multidex.MultiDex
import com.theone.framework.base.BaseApp

/**
 * @Author zhiqiang
 * @Date 2019-06-27
 * @Description
 */
class App : BaseApp() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
