package com.theone.eye.utils

import com.theone.framework.http.ApiResponse
import com.theone.framework.http.exception.NetworkException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.MaybeTransformer
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @Author zhiqiang
 * @Date 2019-06-27
 * @Description Rx相关工具类
 */
object RxUtil {
    /**
     * 1.预处理response数据
     * 2.线程切换
     * 处理后的数据： T
     */
    fun <T> getDataInMainThread(): ObservableTransformer<ApiResponse<T>, T?> {
        return ObservableTransformer { upstream ->
            upstream.map { it ->
                if (it.isSuccess) {
                    it.data
                } else {
                    throw NetworkException(it.statusCode, it.message)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 1.预处理response数据
     * 处理后的数据： T
     */
    fun <T> getData(): ObservableTransformer<ApiResponse<T>, T?> {
        return ObservableTransformer { upstream ->
            upstream.map { it ->
                if (it.isSuccess) {
                    it.data
                } else {
                    throw NetworkException(it.statusCode, it.message)
                }
            }
        }
    }

    /**
     * 2.线程切换
     * 处理后的数据： T
     */
    fun <T> toMainThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    @JvmStatic
    fun <T> maybeToMain(): MaybeTransformer<T, T> {

        return MaybeTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}