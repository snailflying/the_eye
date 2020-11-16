package com.piaoyou.piaoxingqiu.app.entity.api

import android.text.TextUtils
import com.shownow.shownow.base.constant.Constant
import com.theone.eye.home.entity.FloorTypeConstants
import org.json.JSONObject
import java.io.Serializable


/**
 * @Author ZhiQiang
 * @Date 2020/4/9
 * @Description 楼层接口模型
 */
class FloorBean : Serializable {
    //通用核心
    /**
     * 楼层的id
     */
    val id: String? = null

    /**
     * 楼层标题，比如场馆合集
     */
    var title: String? = null

    /**
     * 子标题，一般不展示
     */
    val subTitle: String? = null

    /**
     * 图标，冗余字段
     */
    val iconUrl: String? = null

    /**
     * 点击路由，比如场馆点击进入页面
     */
    val navigateUrl: String? = null

    /**
     * 描述，冗余字段
     */
    val description: String? = null

    /**
     * 楼层样式，tab、one,默认one
     */

    var type: String? = null

    /**
     * 楼层 index ,索引
     */
    val index: Int? = 0

    /**
     * 楼层内room元素
     */
    val rooms: MutableList<FloorRoom>? = mutableListOf()

    /**
     * 标签
     */
    val labels: List<FloorItem>? = null
    //额外参数
    /**
     * "更多"的名称,为null表示不用显示
     */
    val showMore: String? = null

    fun isTabType(): Boolean = TextUtils.equals(FloorTypeConstants.FLOOR_TITLE_TAB, type)

    fun isOneType(): Boolean = TextUtils.equals(FloorTypeConstants.FLOOR_TITLE_ONE, type)

    /**
     * 上一个楼层为title（one或者tab）
     */
    fun isLastTypeTitle(): Boolean = lastType == FloorTypeConstants.FLOOR_TITLE_ONE || lastType == FloorTypeConstants.FLOOR_TITLE_TAB

    /**
     * 上一个楼层为空
     */
    fun isLastTypeNone(): Boolean = lastType == FloorTypeConstants.FLOOR_NONE

    /**
     * 上一个楼层为热抢
     */
    fun isLastTypeHotBuy(): Boolean = lastType == FloorTypeConstants.HOT_BUYING
    /************************************************以下为本地字段 */
    /**
     * 本地字段，用于切换 tab 时，记录选中的 room 位置
     */
    @Transient
    var curPosition: Int = 0

    /**
     * 上一层的楼层样式
     */
    @Transient
    var lastType: String? = null

    /**
     * 本地字段，用于描述间距
     */
    @Transient
    var margin = listOf(Constant.PAGE_MARGIN_HORIZON, Constant.PAGE_MARGIN_VERTICAL_2, Constant.PAGE_MARGIN_HORIZON, 0)

    /**
     * 获取当前Room
     *
     * @return 当前Room
     */
    fun getCurRoom(): FloorRoom {
        if (rooms.isNullOrEmpty()) {
            return FloorRoom()
        }
        return if (rooms!!.size > curPosition) {
            rooms[curPosition]
        } else FloorRoom()
    }

    /************************************************以上为本地字段 */

}