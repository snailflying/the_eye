package com.theone.eye.ui.report.model

import com.theone.eye.R
import com.theone.eye.base.entity.ReportRes
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.utils.RxUtil
import com.theone.framework.base.BaseModel
import com.theone.framework.ext.getString
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ReportModel : BaseModel<ApiService>(ApiService::class.java), IReportModel {
    override fun getTitle1(): String {
        return getString(R.string.left_eye)
    }

    override fun getTitle2(): String {
        return getString(R.string.right_eye)
    }

    override fun getTitle3(): String {
        return getString(R.string.diagnose_result)
    }

    override fun getReportById(appointId: String?): Observable<ApiResponse<ReportRes>> {
        return apiService.getReportById(appointId).compose(RxUtil.toMainThread())
    }

}