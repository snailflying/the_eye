package com.theone.eye.home.entity

import android.graphics.Color
import android.text.TextUtils
import androidx.annotation.ColorInt
import com.shownow.shownow.base.constant.Constant
import com.theone.eye.R
import com.theone.framework.ext.getCompatColor
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 2/22/21
 * @Description 如果是多层结构，从第二层开始，依次放在 subItems/subItems1/subItems2等等内（将层级铺开）
 */
class FloorItemDemo : Serializable {
    //通用核心
    /**
     * item的id
     */
    val id: String = ""

    /**
     * 楼层样式，tab、one,默认one
     */
    var type: String? = null

    /**
     * item标题
     */
    var title: String? = null

    /**
     * 子标题，一般不展示
     */
    var subTitle: String? = null

    /**
     * item图片
     */
    var imgUrl: String? = null

    /**
     * 点击路由
     */
    val navigateUrl: String? = null

    /**
     * 描述，“时间”或“123人想看”等
     */
    val description: String? = null

    /**
     * 价格
     */
    private val price: String? = null

    /**
     * 时间
     */
    val time: Long = 0

    /**
     * 地点
     */
    val address: Long = 0

    /**
     * item index ,索引
     */
    var index: Int? = null

    /**
     * 所有艺人等第一层嵌套
     */
    val subItems: MutableList<FloorItemDemo>? = mutableListOf()

    /**
     * 第二层嵌套
     */
    val subItems1: MutableList<FloorItemDemo>?  = mutableListOf()

    fun isTabType(): Boolean = TextUtils.equals(FloorTypeConstants.FLOOR_TITLE_TAB, type)

    fun isOneType(): Boolean = TextUtils.equals(FloorTypeConstants.FLOOR_TITLE_ONE, type)

    val isOnePlusThree: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_ONE_PLUS_THREE, type)

    val isBanner: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_BANNER, type)

    val isHotBuy: Boolean
        get() = TextUtils.equals(FloorTypeConstants.HOT_BUYING, type)

    val isScrollType: Boolean
        get() = TextUtils.equals(FloorTypeConstants.ROOM_SCROLL, type)

    /************************************************以下为本地字段 */
    /**
     * 本地字段，用于切换 tab 时，记录选中的 room 位置
     */
    @Transient
    var curPosition: Int = 0

    @Transient
    var curPosition1: Int = 0

    /**
     * 本地字段，本地图片资源
     */
    var iconRes: Int? = null

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
     * 上一个楼层为title（one或者tab）
     */
    fun isLastTypeTitle(): Boolean =
        lastType == FloorTypeConstants.FLOOR_TITLE_ONE || lastType == FloorTypeConstants.FLOOR_TITLE_TAB

    /**
     * 上一个楼层为空
     */
    fun isLastTypeNone(): Boolean = lastType == FloorTypeConstants.FLOOR_NONE

    /**
     * 上一个楼层为热抢
     */
    fun isLastTypeHotBuy(): Boolean = lastType == FloorTypeConstants.HOT_BUYING

    /**
     * 获取当前Room
     *
     * @return 当前Room
     */
    fun getCurSubItem(): FloorItemDemo {
        if (subItems.isNullOrEmpty()) {
            return FloorItemDemo()
        }
        return if (subItems.size > curPosition) {
            subItems[curPosition]
        } else FloorItemDemo()
    }

    fun getCurSubItem1(): FloorItemDemo {
        if (subItems1.isNullOrEmpty()) {
            return FloorItemDemo()
        }
        return if (subItems1.size > curPosition1) {
            subItems1[curPosition1]
        } else FloorItemDemo()
    }

    /**********************以下为拓展字段***************************/

    /**
     * 字体颜色
     */
    val textColor: String? = null

    /**
     * 背景颜色
     */
    val bgColor: String? = null

    /**
     * 背景颜色渐变起始色
     */
    val bgStartColor: String? = null

    /**
     * 背景颜色渐变结束色
     */
    val bgEndColor: String? = null

    //额外参数
    /**
     * 标签，芝麻信用、年度最佳音乐剧
     */
    private val labels: MutableList<FloorItemDemo>? = mutableListOf()

    fun getTextColor(): Int {
        return try {
            Color.parseColor(if (TextUtils.isEmpty(textColor)) "#323038" else textColor)
        } catch (e: Exception) {
            Color.parseColor("#323038")
        }
    }


    @ColorInt
    fun getBgColor(@ColorInt defaultColor: Int = getCompatColor(R.color.color_background_1)): Int {
        try {
            return if (!bgColor.isNullOrEmpty()) {
                Color.parseColor(bgColor)
            } else if (!bgStartColor.isNullOrEmpty()) {
                Color.parseColor(bgStartColor)
            } else if (!bgEndColor.isNullOrEmpty()) {
                Color.parseColor(bgEndColor)
            } else {
                defaultColor
            }
        } catch (e: Exception) {
        }
        return defaultColor
    }

    /**
     * 获取第一个标签
     * @return FloorItem?
     */
    fun getLabel(): FloorItemDemo? {
        return if (labels.isNullOrEmpty()) {
            null
        } else labels!![0]
    }
}