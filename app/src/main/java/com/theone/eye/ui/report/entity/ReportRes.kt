package com.theone.eye.base.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author ZhiQiang
 * @Date 2020/12/13
 * @Description
 */

@Parcelize
data class ReportRes(
    var id: Int? = null,
    var clientType: Int? = null,
    var clientId: Int? = null,
    var appointId: Int? = null,
    var leftEyePictures: List<String>? = null,
    var rightEyePictures: List<String>? = null,
    var createTime: String? = null,
    var diagnosisTime: String? = null,
    var diagnosisResultLeft: String? = null,
    var diagnosisResultRight: String? = null,
    /**
     * 处理状态 0 未处理 1 已处理
     */
    var status: String? = null
) : Parcelable