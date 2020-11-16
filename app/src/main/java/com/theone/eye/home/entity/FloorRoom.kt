package com.piaoyou.piaoxingqiu.app.entity.api

import android.net.Uri
import android.text.TextUtils
import com.shownow.shownow.base.constant.Constant
import com.theone.eye.home.entity.FloorTypeConstants
import org.json.JSONObject
import java.io.Serializable

/**
 * 类比于房间
 */
class FloorRoom : Serializable {
    //通用核心
    /**
     * Room的id
     */
    val id: String? = null

    /**
     * 标题，比如场馆标题
     */
    var title: String? = null

    /**
     * 子标题，一般不展示
     */
    val subTitle: String? = null

    /**
     * 图标，比如场馆图标
     */
    private val imgUrl: String? = null

    /**
     * 点击路由，比如场馆点击进入页面
     */
    val navigateUrl: String? = null

    /**
     * 描述，比如"场馆内共有12场演出"
     */
    var description: String? = null

    /**
     * Item样式:ONE_PLUS_THREE、scroll、HOT_BUYING、HOME_PAGE_BANNER
     */
    var type: String? = null

    /**
     * room(房间) index ,索引
     */
    val index = 0

    /**
     * Item内家具元素
     */
    val items: MutableList<FloorItem>? = mutableListOf()
    //额外参数
    /**
     * "更多"的名称,为null表示不用显示
     */
    val showMore: String? = null

    /**
     * 所有巡演关联演出
     */
    val subItems: List<FloorItem>? = null

    val isOnePlusThree: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_ONE_PLUS_THREE, type) && !items.isNullOrEmpty()

    val isBanner: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_BANNER, type)

    val isHotBuy: Boolean
        get() = TextUtils.equals(FloorTypeConstants.HOT_BUYING, type)

    val isScrollType: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_SCROLL, type)

    /**
     * 用于 UI 自动化埋点传入 Id
     *
     * @return
     */
    val uiTestId: String
        get() = id ?: (title ?: "")


    val curItem: FloorItem
        get() = if (items.isNullOrEmpty()) {
            FloorItem()
        } else items!![0]

    /************************************************以下为本地字段 */
    var localFloorTitle //本地字段，用来埋点
            : String? = null
    /**
     * 本地字段，用于描述间距
     */
    @Transient
    var margin = listOf(Constant.PAGE_MARGIN_HORIZON, Constant.PAGE_MARGIN_VERTICAL_2, Constant.PAGE_MARGIN_HORIZON, 0)

    /************************************************以上为本地字段 */
}