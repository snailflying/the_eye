package com.theone.eye.home.entity

import java.io.Serializable

/**
 * @Author zhiqiang
 * @Date 2019-08-13
 * @Description
 */
class HomeEn : Serializable {
    /**
     * 姓名，必须要可空（后台可能返回空）
     */
    val name: String? = ""
}