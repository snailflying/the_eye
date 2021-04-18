package com.theone.eye.base.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description 网络错误
 */
@Parcelize
data class HttpError(
    var code: Int? = -1,
    var msg: String? = null
) : Parcelable