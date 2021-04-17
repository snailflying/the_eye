package com.theone.eye.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author zhiqiang
 * @Date 2019-11-25
 * @Description
 */
abstract class HomeMainViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView!!) {
    var mPaddingLeft = 0
    var mPaddingTop = 0
    var mPaddingRight = 0
    var mPaddingBottom = 0

    constructor(inflater: LayoutInflater, parent: ViewGroup?, @LayoutRes layoutId: Int) : this(inflater.inflate(layoutId, parent, false)) {}

    abstract fun bindViewHolder(t: T)
    fun setPadding(paddingLeft: Int, paddingTop: Int, paddingRight: Int, mPaddingBottom: Int) {
        mPaddingLeft = paddingLeft
        mPaddingTop = paddingTop
        mPaddingRight = paddingRight
        this.mPaddingBottom = mPaddingBottom
    }

    open fun onViewRecycled(holder: HomeMainViewHolder<*>?) {}
}