package com.theone.eye.ui.report.vm

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.shownow.shownow.base.constant.HttpStatusCode
import com.theone.eye.R
import com.theone.eye.base.entity.HttpError
import com.theone.eye.base.entity.ReportRes
import com.theone.eye.ui.report.adapter.ContentOneBinder
import com.theone.eye.ui.report.adapter.ImageOneBinder
import com.theone.eye.ui.report.adapter.TitleBinder
import com.theone.eye.ui.report.model.IReportModel
import com.theone.eye.ui.report.model.ReportModel
import com.theone.framework.base.BaseViewModel
import com.theone.framework.ext.getString

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class ReportViewModel(override var model: IReportModel = ReportModel()) : BaseViewModel<IReportModel>() {

    private val itemsFloor = mutableListOf<Any>()
    private val adapter: MultiTypeAdapter = MultiTypeAdapter(itemsFloor)

    val httpResultLive: MutableLiveData<HttpError> = MutableLiveData()
    fun initRecyclerView(recyclerView: RecyclerView) {
        adapter.register(TitleBinder())
        adapter.register(ContentOneBinder())

        adapter.register(ImageOneBinder())

        recyclerView.adapter = adapter
    }

    private fun setFloorData(data: ReportRes) {
        if (!data.leftEyePictures.isNullOrEmpty()) {
            itemsFloor.add(TitleBinder.Data(model.getTitle1()))
            data.leftEyePictures!!.forEach {
                itemsFloor.add(ImageOneBinder.Data(it))
            }
        }
        if (!data.rightEyePictures.isNullOrEmpty()) {
            itemsFloor.add(TitleBinder.Data(model.getTitle2()))
            data.rightEyePictures!!.forEach {
                itemsFloor.add(ImageOneBinder.Data(it))
            }
        }
        itemsFloor.add(TitleBinder.Data(model.getTitle3()))
        if (!data.diagnosisResultLeft.isNullOrEmpty()) {
            itemsFloor.add(ContentOneBinder.Data(model.getTitle1()))
            itemsFloor.add(ContentOneBinder.Data(data.diagnosisResultLeft!!))
        }
        if (!data.diagnosisResultRight.isNullOrEmpty()) {
            itemsFloor.add(ContentOneBinder.Data(model.getTitle2()))
            itemsFloor.add(ContentOneBinder.Data(data.diagnosisResultRight!!))
        }
        adapter.notifyDataSetChanged()
    }

    fun getReportById(appointId: String?) {
        model.getReportById(appointId)
            .subscribe(object : BaseObserver<ReportRes>() {
                override fun onResultSuccess(data: ReportRes?) {
                    if (data != null) {
                        httpResultLive.value = HttpError(HttpStatusCode.SUCCESS)
                        setFloorData(data)
                    } else {
                        httpResultLive.value = HttpError(HttpStatusCode.NETWORK_EMPTY, getString(R.string.http_empty))
                    }
                }

                override fun onResultFailed(statusCode: Int, comments: String?) {
                    httpResultLive.value = HttpError(statusCode, comments)
                }

                override fun onError(e: Throwable?) {
                    httpResultLive.value = HttpError()
                }

            })
    }
}