package com.theone.eye.ui.home.model

import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.report.model.IReportModel
import com.theone.framework.base.BaseModel

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class AppointmentModel : BaseModel<ApiService>(ApiService::class.java), IAppointmentModel {


    companion object {

        /**
         * 首页banner图片地址
         */
        const val HOME_BANNER = "https://static1.piaoxingqiu.cn/PXQ/assets/img/app/home_banner.png"
    }

}