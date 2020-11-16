package com.piaoyou.piaoxingqiu.app.entity.api

import android.graphics.Color
import android.text.TextUtils
import androidx.annotation.ColorInt
import com.theone.eye.R
import com.theone.framework.ext.getCompatColor
import com.theone.framework.ext.getString
import java.io.Serializable

/**
 * @Author zhiqiang
 * @Date 2020/3/19
 * @Description Floor模型的第三层，类比于家具
 */
class FloorItem : Serializable {
    //通用核心
    /**
     * item的id
     */
    val id: String = ""

    /**
     * item标题
     */
    val title: String? = null

    /**
     * item图片
     */
    private var imgUrl: String? = null

    /**
     * 点击路由
     */
    val navigateUrl: String? = null

    /**
     * 描述，“时间”或“123人想看”等
     */
    val description: String? = null

    /**
     * "更多"的图片,为null表示不用显示
     */
    val showMoreImg: String? = null
    //额外参数
    /**
     * 标签，芝麻信用、年度最佳音乐剧
     */
    private val labels: List<FloorItem>? = null

    /**
     * 子标题，一般不展示
     */
    val subTitle: String? = null
    //票价信息
    /**
     * 价格
     */
    private val price: String? = null

    /**
     * 折扣
     */
    private val discount: String? = null

    /**
     * 预售时间
     */
    val saleTime: Long = 0

    /**
     * item index ,索引
     */
    var index: Int? = null

    /**
     * 所有艺人
     */
    val subItems: List<FloorItem>? = null

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

    /**
     * 优惠信息0，年度最佳音乐剧1，图片2，芝麻信用3
     */
    val type: Int? = 0

    fun getTextColor(): Int {
        return try {
            Color.parseColor(if (TextUtils.isEmpty(textColor)) "#323038" else textColor)
        } catch (e: Exception) {
            Color.parseColor("#323038")
        }
    }

    fun getImageUrl(): String {
        return imgUrl ?: ""
    }

    fun setImageUrl(url: String) {
        imgUrl = url
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
    fun getLabel(): FloorItem? {
        return if (labels.isNullOrEmpty()) {
            null
        } else labels!![0]
    }

    /**
     * 获取第一个SubItem
     * @return FloorItem?
     */
    fun getSubItem(): FloorItem? {
        return if (subItems.isNullOrEmpty()) {
            null
        } else subItems!![0]
    }

    fun getPriceWithSymbol(): String {
        return if (TextUtils.isEmpty(price)) {
            getString(R.string.rmb_symbol) + "0"
        } else getString(R.string.rmb_symbol) + price
    }

    fun isShowPrice(): Boolean {
        return !TextUtils.isEmpty(price) && !TextUtils.equals(price, "0")
    }

    fun getDiscount(): String {
        return if (TextUtils.isEmpty(discount)) "" else discount!!.substring(0, Math.min(discount.length, 3))
    }

    fun isShowDiscount(): Boolean {
        return !TextUtils.isEmpty(discount)
    }

    fun isShowSaleTime(): Boolean {
        val millisLeft = saleTime - System.currentTimeMillis()
        return millisLeft > 0
    }

    fun getArtistsText(): String {
        if (subItems.isNullOrEmpty()) {
            return ""
        }
        val sb = StringBuilder()
        for (i in subItems!!.indices) {
            if (i > 2) {
                sb.append("等")
                break
            }
            if (i != 0) {
                sb.append("、")
            }
            val artist = subItems[i]!!.title
            sb.append(artist)
        }
        return sb.toString()
    }

    /************************************************以下为本地字段 */
    /**
     * 来自页面
     */
    @Transient
    var fromPage: String? = null

    /**
     * 是否展示index排序
     */
    fun isShowIndex(): Boolean = index != null && index!! > 0

    /************************************************以上为本地字段 */
}