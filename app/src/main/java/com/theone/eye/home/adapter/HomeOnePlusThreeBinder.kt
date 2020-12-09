package com.theone.eye.home.adapter

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
import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.piaoyou.piaoxingqiu.app.entity.api.FloorItem
import com.theone.eye.R
import com.theone.eye.databinding.RecycleHomeFloorOnePlusThreeLittleBinding
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.dp2px
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

class HomeOnePlusThreeBinder : ItemViewBinder<HomeOnePlusThreeBinder.HomeOnePlusThreeLittleEn, HomeOnePlusThreeBinder.HomeOnePlusThreeHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HomeOnePlusThreeHolder {
        return HomeOnePlusThreeHolder(RecycleHomeFloorOnePlusThreeLittleBinding.inflate(inflater, parent, false).root)
    }

    override fun onBindViewHolder(holder: HomeOnePlusThreeHolder, item: HomeOnePlusThreeLittleEn) {
        holder.bindViewHolder(item)
    }

    class HomeOnePlusThreeLittleEn(floorBean: FloorBean?) : HomeBaseOnePlusThreeEn(floorBean)

    class HomeOnePlusThreeHolder(rootView: View) : HomeMainViewHolder<HomeBaseOnePlusThreeEn>(rootView) {
        private val mContext: Context = itemView.context
        private val mImageRadius: Float = dp2px( 4f).toFloat()
        private val mClRootView: ConstraintLayout = rootView.findViewById(R.id.clRootView)
        override fun bindViewHolder(homeTopicEn: HomeBaseOnePlusThreeEn) {
            val itemBeans = homeTopicEn.list
            itemView.setPadding(dp2px( homeTopicEn.marginLeft.toFloat()),
                dp2px( homeTopicEn.marginTop.toFloat()),
                dp2px(homeTopicEn.marginRight.toFloat()),
                dp2px( homeTopicEn.marginBottom.toFloat()))
            val size = itemBeans.size
            if (size == 1) {
                mClRootView.getChildAt(0).visibility = View.VISIBLE
                mClRootView.getChildAt(1).visibility = View.GONE
                mClRootView.getChildAt(2).visibility = View.GONE
                mClRootView.getChildAt(3).visibility = View.GONE
                mClRootView.getChildAt(4).visibility = View.GONE
                mClRootView.getChildAt(5).visibility = View.GONE
                setDescShow(homeTopicEn.floorBean, itemBeans[0], 0)
            } else if (size == 2) {
                mClRootView.getChildAt(0).visibility = View.GONE
                mClRootView.getChildAt(1).visibility = View.VISIBLE
                mClRootView.getChildAt(2).visibility = View.VISIBLE
                mClRootView.getChildAt(3).visibility = View.GONE
                mClRootView.getChildAt(4).visibility = View.GONE
                mClRootView.getChildAt(5).visibility = View.GONE
                setDescShow(homeTopicEn.floorBean, itemBeans[0], 1)
                setDescShow(homeTopicEn.floorBean, itemBeans[1], 2)
            } else if (size > 2) {
                mClRootView.getChildAt(0).visibility = View.GONE
                mClRootView.getChildAt(1).visibility = View.GONE
                mClRootView.getChildAt(2).visibility = View.GONE
                mClRootView.getChildAt(3).visibility = View.VISIBLE
                mClRootView.getChildAt(4).visibility = View.VISIBLE
                mClRootView.getChildAt(5).visibility = View.VISIBLE
                setDescShow(homeTopicEn.floorBean, itemBeans[0], 3)
                setDescShow(homeTopicEn.floorBean, itemBeans[1], 4)
                setDescShow(homeTopicEn.floorBean, itemBeans[2], 5)
            } else {
                mClRootView.visibility = View.GONE
            }
        }

        private fun setDescShow(floorBean: FloorBean?, itemBean: FloorItem?, viewIndex: Int) {
            if (null == itemBean) {
                return
            }
            val parent = mClRootView.getChildAt(viewIndex)
            if (itemBean.getLabel() != null) {
                val tvLabel: TextView = parent.findViewById(R.id.tvLabel)
                tvLabel.visibility = View.VISIBLE
                tvLabel.text = itemBean.getLabel()!!.title
            }
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

            val ivPoster: ImageView = parent.findViewById(R.id.ivPoster)

            ivPoster.clickWithTrigger {
                //todo
                Router.build(itemBean.navigateUrl).go(mContext)
//            Router.build("https://mpxq-qa.piaoxingqiu.cn/topic-activity/5ecf3b726c622948c4ab831e?hideNavigationBar=1").go(mContext)
            }
        }

        override fun onViewRecycled(holder: HomeMainViewHolder<*>?) {}

    }

    open class HomeBaseOnePlusThreeEn(floorBean: FloorBean?) : Serializable {
        private val bannerEns: List<FloorItem>?
        val floorBean: FloorBean?

        val list: List<FloorItem>
            get() = bannerEns ?: ArrayList()

        val marginLeft: Int
            get() = floorBean!!.getCurRoom().margin[0]

        val marginTop: Int
            get() = floorBean!!.getCurRoom().margin[1]

        val marginRight: Int
            get() = floorBean!!.getCurRoom().margin[2]

        val marginBottom: Int
            get() = floorBean!!.getCurRoom().margin[3]

        init {
            val roomBean = floorBean!!.getCurRoom()
            this.floorBean = floorBean
            bannerEns = roomBean.items
        }
    }
}

