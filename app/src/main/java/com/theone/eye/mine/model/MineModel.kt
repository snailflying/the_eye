package com.theone.eye.home.model

import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.piaoyou.piaoxingqiu.app.entity.api.FloorItem
import com.piaoyou.piaoxingqiu.app.entity.api.FloorRoom
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.home.entity.FloorTypeConstants
import com.theone.framework.base.BaseModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class MineModel : BaseModel<ApiService>(ApiService::class.java), IMineModel {


    companion object {

        /**
         * 首页banner图片地址
         */
        const val HOME_BANNER = "https://static1.piaoxingqiu.cn/PXQ/assets/img/app/home_banner.png"
    }

}