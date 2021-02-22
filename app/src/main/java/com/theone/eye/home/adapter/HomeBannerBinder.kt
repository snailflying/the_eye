package com.theone.eye.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.drakeet.multitype.ItemViewBinder
import com.piaoyou.piaoxingqiu.app.entity.api.FloorItem
import com.theone.eye.R
import com.theone.eye.databinding.ItemHomeFloorBannerImgBinding
import com.theone.eye.databinding.RecycleHomeFloorBannerItemBinding
import com.theone.eye.home.HomeEventConstants
import com.theone.eye.home.entity.FloorItemDemo
import com.theone.framework.ext.clickWithTrigger
import com.theone.framework.ext.dp2px
import com.theone.framework.ext.getColor
import com.theone.framework.image.AppImageLoader
import com.theone.framework.router.AppRouter
import com.theone.framework.widget.banner.Banner
import com.theone.framework.widget.banner.IndicatorView
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.Serializable
import java.util.*

/**
 * @Author zhiqiang
 * @Date 2019-12-10
 * @Description
 */
class HomeBannerBinder : ItemViewBinder<HomeBannerBinder.HomeBannerEn, HomeBannerBinder.HomeBannerHolder>() {

    var holder: HomeBannerHolder? = null

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HomeBannerHolder {
        holder = HomeBannerHolder(RecycleHomeFloorBannerItemBinding.inflate(inflater, parent, false))
        return holder!!
    }

    override fun onBindViewHolder(holder: HomeBannerHolder, item: HomeBannerEn) {
        holder.bindViewHolder(item)
    }

    fun startBannerTurning() {
        holder?.startBannerTurning()
    }

    fun stopBannerTurning() {
        holder?.stopBannerTurning()
    }

    inner class HomeBannerHolder constructor(binding: RecycleHomeFloorBannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mBanner: Banner = itemView.findViewById(R.id.banner)
        var finalBannerList: List<HomeBannerEn.BannerEn>? = mutableListOf()


        fun startBannerTurning() {
            mBanner.startTurning()
        }

        fun stopBannerTurning() {
            mBanner.stopTurning()
        }

        fun bindViewHolder(homeBannerEntryEn: HomeBannerEn) {
            val param = mBanner.layoutParams as? ViewGroup.MarginLayoutParams
            param?.setMargins(
                0,
                dp2px(homeBannerEntryEn.marginTop.toFloat()),
                0,
                dp2px(homeBannerEntryEn.marginBottom.toFloat())
            )

            setBannerCycleView(homeBannerEntryEn)
        }

        private fun setBannerCycleView(homeBannerEn: HomeBannerEn) {
            finalBannerList = homeBannerEn.list
            if (finalBannerList.isNullOrEmpty()) {
                itemView.visibility = View.GONE
                return
            }

            val indicator = IndicatorView(mBanner.context).apply {
                setIndicatorRadius(2f)
                setIndicatorColor(getColor(R.color.color_background_2))
                setIndicatorSelectedRatio(2.5f)
                setIndicatorSpacing(2f)
                setIndicatorSelectorColor(getColor(R.color.color_background_1))
            }
            val listener = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            }
            mBanner.setAutoTurningTime(BANNER_DELAY_TIME)
                .setIndicator(indicator)
                .setPageMargin(0, dp2px(homeBannerEn.marginLeft.toFloat()))
                .setOuterPageChangeListener(listener)
                .adapter = ImageAdapter( finalBannerList!!)
        }


    }

    private companion object {
        private const val BANNER_DELAY_TIME = 5000L
    }

    internal class ImageAdapter(private val finalBannerList: List<HomeBannerEn.BannerEn?>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val item: ImageView =
                ItemHomeFloorBannerImgBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
            return ImageViewHolder(item)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val imageViewHolder: ImageViewHolder = holder as ImageViewHolder
            /*imageViewHolder.image.setImageURI(items[position])*/
            AppImageLoader.with(imageViewHolder.image)
                .load(finalBannerList[position]?.imgUrl)
                .placeholder(R.drawable.app_default_img_banner)
                .error(R.drawable.app_default_img_banner)
                .transform(
                    CenterCrop(),
                    RoundedCornersTransformation(dp2px(6f), 0, RoundedCornersTransformation.CornerType.ALL)
                )
                .into(imageViewHolder.image)

            imageViewHolder.image.clickWithTrigger {
                if (!finalBannerList.isNullOrEmpty() && finalBannerList.size > position) {
                    AppRouter.build(finalBannerList[position]!!.navigateUrl).go(imageViewHolder.image.context)
                }
            }
        }

        override fun getItemCount(): Int {
            return finalBannerList.size
        }

    }

    internal class ImageViewHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView) {
        internal val image: ImageView = itemView as ImageView
    }

    class HomeBannerEn(val floorBean: FloorItemDemo) : Serializable {
        //轮播图
        private val roomBean: FloorItemDemo = floorBean.getCurSubItem()
        val list: List<BannerEn>?

        val marginLeft: Int
            get() = roomBean.margin[0]

        val marginTop: Int
            get() = roomBean.margin[1]

        val marginRight: Int
            get() = roomBean.margin[2]

        val marginBottom: Int
            get() = roomBean.margin[3]

        init {
            //因后台不支持，手动设置margin
            if (floorBean.isLastTypeHotBuy() || floorBean.isLastTypeNone()) {
                roomBean.margin = listOf(
                    HomeEventConstants.HOME_FLOOR_MARGIN_LEFT,
                    HomeEventConstants.HOME_FLOOR_FIRST_TOP,
                    HomeEventConstants.HOME_FLOOR_MARGIN_RIGHT,
                    0
                )
            } else if (floorBean.isLastTypeTitle()) {
                roomBean.margin = listOf(
                    HomeEventConstants.HOME_FLOOR_MARGIN_LEFT,
                    HomeEventConstants.HOME_FLOOR_MARGIN_TOP_FOR_TITLE,
                    HomeEventConstants.HOME_FLOOR_MARGIN_RIGHT,
                    0
                )
            } else {
                roomBean.margin = listOf(
                    HomeEventConstants.HOME_FLOOR_MARGIN_LEFT,
                    HomeEventConstants.HOME_FLOOR_MARGIN_TOP,
                    HomeEventConstants.HOME_FLOOR_MARGIN_RIGHT,
                    0
                )
            }
            list = floorBean.subItems?.map {
                BannerEn(it.imgUrl, it.navigateUrl)
            }
        }

        data class BannerEn(
            val imgUrl: String?,
            val navigateUrl: String?
        ) : Serializable
    }
}