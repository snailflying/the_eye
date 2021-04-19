package com.theone.eye.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chenenyu.router.Router
import com.drakeet.multitype.ItemViewBinder
import com.theone.eye.R
import com.theone.eye.databinding.RecycleHomeFloorOneBinding
import com.theone.eye.ui.home.entity.FloorItemDemo
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.dp2px
import com.theone.framework.ext.getString
import com.theone.framework.widget.toast.ToastUtil
import java.io.Serializable
import java.util.*

/**
 * @Author zhiqiang
 * @Date 2020-5-19
 * @Description 一张横图。
 * _________________________________
 * |                               |
 * |                               |
 * |_______________________________|
 */

class HomeOneReportBinder :
    ItemViewBinder<HomeOneReportBinder.HomeOneData, HomeOneReportBinder.HomeOnePlusThreeHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HomeOnePlusThreeHolder {
        return HomeOnePlusThreeHolder(RecycleHomeFloorOneBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: HomeOnePlusThreeHolder, item: HomeOneData) {
        holder.bindViewHolder(item)
    }

    class HomeOnePlusThreeHolder(binding: RecycleHomeFloorOneBinding) : HomeMainViewHolder<HomeOneData>(binding.root) {
        private val mContext: Context = itemView.context
        private val view: ImageView = binding.ivRootView
        override fun bindViewHolder(t: HomeOneData) {
            val param = itemView.layoutParams as? ViewGroup.MarginLayoutParams
            param?.setMargins(
                0,
                dp2px(t.marginTop.toFloat()),
                0,
                dp2px(t.marginBottom.toFloat())
            )

            val itemBeans = t.list
            val size = itemBeans.size
            if (size != 0) {
                setDescShow(t.list[0])
            } else {
                view.visibility = View.GONE
            }
        }

        private fun setDescShow(itemBean: HomeOneData.HomeOneEn?) {
            if (null == itemBean) {
                return
            }

            if (itemBean.iconRes != null) {
                view.setBackgroundResource(itemBean.iconRes)
            }
            view.clickWithTrigger {
                if (!itemBean.navigateUrl.isNullOrEmpty()) {
                    Router.build(itemBean.navigateUrl).go(mContext)
                } else {
                    ToastUtil.show(getString(R.string.comming_soon))
                }
            }
        }

    }

    class HomeOneData(private val floorBean: FloorItemDemo?) : Serializable {

        private val roomEns: List<HomeOneEn>? = floorBean?.subItems?.map { floorItem ->
            HomeOneEn(floorItem.iconRes, floorItem.navigateUrl)
        }

        val list: List<HomeOneEn>
            get() = roomEns ?: ArrayList()

        val marginLeft: Int
            get() = floorBean!!.margin[0]

        val marginTop: Int
            get() = floorBean!!.margin[1]

        val marginRight: Int
            get() = floorBean!!.margin[2]

        val marginBottom: Int
            get() = floorBean!!.margin[3]

        data class HomeOneEn(
            val iconRes: Int?,
            val navigateUrl: String?
        ) : Serializable
    }
}

