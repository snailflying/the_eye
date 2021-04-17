package com.theone.eye.ui.home.model

import com.theone.eye.R
import com.theone.eye.base.net.api.ApiService
import com.theone.eye.ui.home.entity.FloorItemDemo
import com.theone.eye.ui.home.entity.FloorTypeConstants
import com.theone.framework.base.BaseModel
import com.theone.framework.ext.getString
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class HomeModel : BaseModel<ApiService>(ApiService::class.java), IHomeModel {

    override fun getHomeFloor(): Observable<ApiResponse<List<FloorItemDemo>>> {
        val data = mutableListOf<FloorItemDemo>()
        data.add(mockBanner())
        data.add(mockOnePlusThree())
        data.add(mockOneReport())
        return Observable.just(ApiResponse<List<FloorItemDemo>>(data = data, statusCode = 200, message = ""))
    }

    private fun mockBanner(): FloorItemDemo {
        return FloorItemDemo().also {
            it.type = FloorTypeConstants.FLOOR_TITLE_ONE
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_BANNER
                imgUrl = (HOME_BANNER)
            })
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_BANNER
                imgUrl = (HOME_BANNER)
            })
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_BANNER
                imgUrl = (HOME_BANNER)
            })
        }
    }

    private fun mockOnePlusThree(): FloorItemDemo {
        return FloorItemDemo().also {
            it.type = FloorTypeConstants.FLOOR_TITLE_ONE
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_ONE_PLUS_THREE
                title = getString(R.string.vip_title1)
                subTitle = getString(R.string.vip_subtitle1)
                iconRes = R.drawable.ic_oppointment
            })
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_ONE_PLUS_THREE
                title = getString(R.string.vip_title2)
                subTitle = getString(R.string.vip_subtitle2)
                iconRes = R.drawable.ic_vip1
            })
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_ONE_PLUS_THREE
                title = getString(R.string.vip_title3)
                subTitle = getString(R.string.vip_subtitle3)
                iconRes = R.drawable.ic_vip2
            })
        }
    }
    private fun mockOneReport(): FloorItemDemo {
        return FloorItemDemo().also {
            it.type = FloorTypeConstants.FLOOR_TITLE_ONE
            it.subItems?.add(FloorItemDemo().apply {
                this.type = FloorTypeConstants.ROOM_ONE_REPORT
                iconRes = R.drawable.home_report_bg
            })
        }
    }
    companion object {

        /**
         * 首页banner图片地址
         */
        const val HOME_BANNER = "https://static1.piaoxingqiu.cn/PXQ/assets/img/app/home_banner.png"
    }

}