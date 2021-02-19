package com.theone.eye.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.theone.eye.R
import com.theone.eye.home.entity.BottomBarEn
import com.theone.framework.widget.smarttablayout.SmartTabAdapter

/**
 * @Author ZhiQiang
 * @Date 2020/12/10
 * @Description
 */
class BottomSmartTabAdapter(
    var layoutResId: Int) : SmartTabAdapter<BottomSmartTabAdapter.ViewHolder>() {
    private val mDataList = mutableListOf<BottomBarEn>()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val iconImg: ImageView = viewHolder.itemView.findViewById(R.id.iconIv)
        val customText: TextView = viewHolder.itemView.findViewById(R.id.titleTv)
        iconImg.setImageResource(getTabIconRes(position))
        val isSelect = currTabIndex == position
        viewHolder.itemView.isSelected = isSelect
        customText.isSelected = isSelect
        customText.text = getTabTitle(position)
    }

    override fun getCount(): Int {
        return mDataList.size
    }

    fun setData(data: List<BottomBarEn>) {
        mDataList.clear()
        data.let {
            mDataList.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun getTabIconRes(position: Int): Int {
        if (mDataList.size >= position) {
            return mDataList[position].iconRes
        }
        return 0
    }

    private fun getTabTitle(position: Int): String? {
        if (mDataList.size >= position) {
            return mDataList[position].title
        }
        return null
    }

    class ViewHolder(item: View) : Holder(item)

}
