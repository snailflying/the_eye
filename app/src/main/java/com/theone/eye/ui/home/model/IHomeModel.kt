package com.theone.eye.ui.home.model

import com.themone.core.base.IModel
import com.theone.eye.ui.home.entity.FloorItemDemo
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IHomeModel : IModel {

    /**
     * 加载数据列表
     *
     * @param params
     * @return
     */
    fun getHomeFloor(): Observable<ApiResponse<List<FloorItemDemo>>>
}