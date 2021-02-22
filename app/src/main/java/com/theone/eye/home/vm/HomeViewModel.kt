package com.theone.eye.home.vm

import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.theone.eye.home.adapter.HomeBannerBinder
import com.theone.eye.home.adapter.HomeOnePlusThreeBinder
import com.theone.eye.home.entity.FloorItemDemo
import com.theone.eye.home.entity.FloorTypeConstants
import com.theone.eye.home.model.HomeModel
import com.theone.eye.home.model.IHomeModel
import com.theone.framework.base.BaseViewModel
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class HomeViewModel(override var model: IHomeModel = HomeModel()) : BaseViewModel<IHomeModel>() {
    private val itemsFloor = CopyOnWriteArrayList<Any>()
    private val mHomeMultiAdapter: MultiTypeAdapter = MultiTypeAdapter(itemsFloor)

    /**
     * 当前楼层的上一层Floor样式
     */
    private var topFloorItemType = FloorTypeConstants.FLOOR_NONE

    fun initRecyclerView(recyclerView: RecyclerView) {
        //Banner
        /**
         * Banner切换时的回调
         */
        val homeBannerBinder = HomeBannerBinder()
        val homeOnePlusThreeBinder = HomeOnePlusThreeBinder()

        mHomeMultiAdapter.register(homeBannerBinder)
        mHomeMultiAdapter.register(homeOnePlusThreeBinder)

        recyclerView.adapter = mHomeMultiAdapter
    }

    fun getData() {
        model.getHomeFloor()
            .subscribe(object : BaseObserver<List<FloorItemDemo>>() {
                override fun onResultSuccess(data: List<FloorItemDemo>?) {
                    if (data == null) return
                    distributeFloorBean(data)
                    refreshPageUi()
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                }

                override fun onError(e: Throwable?) {
                }

            })
    }

    private fun refreshPageUi() {
        mHomeMultiAdapter.notifyDataSetChanged()
    }

    /**
     * 楼层数据分发
     *
     * @param floors
     */

    private fun distributeFloorBean(floors: List<FloorItemDemo>?) {
        for (floorEn in floors!!) {
            if (floorEn.isTabType()) {
                addFloorTitleTab(floorEn)
            } else {
                addFloorTitleOne(floorEn)
            }
            addRoomTypes(floorEn)
        }
    }

    /**
     * 标题One赋值
     *
     * @param floorBean
     */
    private fun addFloorTitleOne(floorBean: FloorItemDemo) {
        if (!TextUtils.isEmpty(floorBean.title)) {
            floorBean.lastType = topFloorItemType
            topFloorItemType = FloorTypeConstants.FLOOR_TITLE_ONE
//            itemsFloor.add(HomeTitleOneEn(floorBean))
        }
    }

    /**
     * 标题Tab赋值
     *
     * @param floorBean
     */

    private fun addFloorTitleTab(floorBean: FloorItemDemo?) {

    }

    /**
     * Room赋值
     *
     * @param floorBean
     */

    private fun addRoomTypes(floorBean: FloorItemDemo?) {
        val curRoom = floorBean!!.getCurSubItem()
        when {
            curRoom.isBanner -> {
                //设置上一个楼层样式
                floorBean.lastType = topFloorItemType
                topFloorItemType = FloorTypeConstants.ROOM_BANNER
                itemsFloor.add(HomeBannerBinder.HomeBannerEn(floorBean))
            }
            curRoom.isOnePlusThree -> {
                //设置上一个楼层样式
                floorBean.lastType = topFloorItemType
                topFloorItemType = FloorTypeConstants.ROOM_ONE_PLUS_THREE
                itemsFloor.add(HomeOnePlusThreeBinder.HomeOnePlusThreeEn(floorBean))
            }

        }
    }
}