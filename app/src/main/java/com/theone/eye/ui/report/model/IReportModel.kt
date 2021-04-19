package com.theone.eye.ui.report.model

import com.themone.core.base.IModel
import com.theone.eye.base.entity.ReportRes
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IReportModel : IModel {
    //左眼
    fun getTitle1(): String

    //右眼
    fun getTitle2(): String

    //诊断结果
    fun getTitle3(): String
    fun getReportById(appointId: String?): Observable<ApiResponse<List<ReportRes>>>

}