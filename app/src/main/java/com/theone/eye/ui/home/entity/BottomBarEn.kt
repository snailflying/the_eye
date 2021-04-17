package com.theone.eye.ui.home.entity

import androidx.annotation.DrawableRes
import java.io.Serializable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
data class BottomBarEn(
    /**
     *  标题
     */
    var title: String, @DrawableRes var iconRes: Int
) : Serializable