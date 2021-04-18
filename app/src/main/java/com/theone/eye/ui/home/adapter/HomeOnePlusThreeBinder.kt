package com.theone.eye.ui.home.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chenenyu.router.Router
import com.drakeet.multitype.ItemViewBinder
import com.theone.eye.R
import com.theone.eye.databinding.RecycleHomeFloorOnePlusThreeBinding
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
 * @Description 专题跟一拖三整体布局类似，除两个小图不同。
 * _________________________________
 * |                               |
 * |                               |
 * ---------------------------------
 * 或
 * ________________  _______________
 * |              |  |             |
 * |              |  |             |
 * ----------------  ---------------
 * 或
 * ________________  _______________
 * |              |  |             |
 * |              |  |             |
 * |              |  ---------------
 * |              |  _______________
 * |              |  |             |
 * |              |  |             |
 * ----------------  ---------------
 */

class HomeOnePlusThreeBinder :
    ItemViewBinder<HomeOnePlusThreeBinder.HomeOnePlusThreeEn, HomeOnePlusThreeBinder.HomeOnePlusThreeHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HomeOnePlusThreeHolder {
        return HomeOnePlusThreeHolder(RecycleHomeFloorOnePlusThreeBinding.inflate(inflater, parent, false).root)
    }

    override fun onBindViewHolder(holder: HomeOnePlusThreeHolder, item: HomeOnePlusThreeEn) {
        holder.bindViewHolder(item)
    }

    class HomeOnePlusThreeHolder(rootView: View) : HomeMainViewHolder<HomeOnePlusThreeEn>(rootView) {
        private val mContext: Context = itemView.context
        private val mImageRadius: Float = dp2px(4f).toFloat()
        private val mClRootView: ConstraintLayout = rootView.findViewById(R.id.clRootView)
        override fun bindViewHolder(homeTopicEn: HomeOnePlusThreeEn) {
            val itemBeans = homeTopicEn.list
            itemView.setPadding(
                dp2px(homeTopicEn.marginLeft.toFloat()),
                dp2px(homeTopicEn.marginTop.toFloat()),
                dp2px(homeTopicEn.marginRight.toFloat()),
                dp2px(homeTopicEn.marginBottom.toFloat())
            )
            val size = itemBeans.size
            if (size != 0) {
                mClRootView.getChildAt(0).visibility = View.VISIBLE
                mClRootView.getChildAt(1).visibility = View.VISIBLE
                mClRootView.getChildAt(2).visibility = View.VISIBLE
                setDescShow(itemBeans[0], 0)
                setDescShow(itemBeans[1], 1)
                setDescShow(itemBeans[2], 2)
            } else {
                mClRootView.visibility = View.GONE
            }
        }

        private fun setDescShow(itemBean: HomeOnePlusThreeEn.OnePlusThreeEn?, viewIndex: Int) {
            if (null == itemBean) {
                return
            }
            val parent = mClRootView.getChildAt(viewIndex)
            if (!TextUtils.isEmpty(itemBean.title)) {
                val tvTitle: TextView = parent.findViewById(R.id.tvTitle)
                tvTitle.visibility = View.VISIBLE
                tvTitle.text = itemBean.title
            }
            if (!TextUtils.isEmpty(itemBean.subTitle)) {
                val tvSubTitle: TextView = parent.findViewById(R.id.tvSubTitle)
                tvSubTitle.visibility = View.VISIBLE
                tvSubTitle.text = itemBean.subTitle
            }

            if (itemBean.iconRes != null) {
                val ivPoster: ImageView = parent.findViewById(R.id.ivPoster)
                ivPoster.setImageResource(itemBean.iconRes)
            }
            parent.clickWithTrigger {
                if (itemBean.navigateUrl.isNullOrEmpty()) {
                    ToastUtil.show(getString(R.string.comming_soon))
                } else {
                    Router.build(itemBean.navigateUrl).go(mContext)
                }
            }
        }

        override fun onViewRecycled(holder: HomeMainViewHolder<*>?) {}

    }

    class HomeOnePlusThreeEn(private val floorBean: FloorItemDemo?) : Serializable {

        private val bannerEns: List<OnePlusThreeEn>? = floorBean?.subItems?.map { floorItem ->
            OnePlusThreeEn(floorItem.title, floorItem.subTitle, floorItem.iconRes, floorItem.navigateUrl)
        }

        val list: List<OnePlusThreeEn>
            get() = bannerEns ?: ArrayList()

        val marginLeft: Int
            get() = floorBean!!.margin[0]

        val marginTop: Int
            get() = floorBean!!.margin[1]

        val marginRight: Int
            get() = floorBean!!.margin[2]

        val marginBottom: Int
            get() = floorBean!!.margin[3]

        data class OnePlusThreeEn(
            val title: String?,
            val subTitle: String?,
            val iconRes: Int?,
            val navigateUrl: String?
        ) : Serializable
    }
}

