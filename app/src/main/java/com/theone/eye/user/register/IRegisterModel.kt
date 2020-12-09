package com.theone.eye.user.login

import com.piaoyou.piaoxingqiu.app.entity.api.FloorBean
import com.themone.core.base.IModel
import com.theone.framework.http.ApiResponse
import io.reactivex.rxjava3.core.Observable

/**
 * @Author ZhiQiang
 * @Date 2020/11/16
 * @Description
 */
interface IRegisterModel:IModel {

    /**
     * 加载数据列表
     *
     * @param params
     * @return
     */
    fun getHomeFloor(): Observable<ApiResponse<FloorBean>>
}