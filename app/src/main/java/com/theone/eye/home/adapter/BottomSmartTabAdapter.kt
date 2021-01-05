package com.theone.eye.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.theone.framework.widget.smarttablayout.SmartTabAdapter

/**
 * @Author ZhiQiang
 * @Date 2020/12/10
 * @Description
 */
class BottomSmartTabAdapter(
    var layoutResId: Int,
    var tabViewTextViewId: Int = View.NO_ID
) : SmartTabAdapter<BottomSmartTabAdapter.ViewHolder>() {
    private val mDataList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val customText: View =
            if (tabViewTextViewId != View.NO_ID) {
                viewHolder.itemView.findViewById(tabViewTextViewId)
            } else {
                viewHolder.itemView
            }

        val isSelect = currTabIndex == position
        viewHolder.itemView.isSelected = isSelect
        customText.isSelected = isSelect
        if (customText is TextView) {
            customText.text = getTabTitle(position)
            onBindViewHolderWrapper(customText, isSelect)
        }
    }

    override fun getCount(): Int {
        return mDataList.size
    }

    fun setData(data: List<String>) {
        mDataList.clear()
        data.let {
            mDataList.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun onBindViewHolderWrapper(textView: TextView, isSelect: Boolean) {
        /*if (isSelect) {
            textView.setTextColor(getColor(R.color.white))
        } else {
            textView.setTextColor(getColor(R.color.color_333333))
        }*/
    }

    private fun getTabTitle(position: Int): String? {
        if (mDataList.size >= position) {
            return mDataList[position]
        }
        return null
    }

    class ViewHolder(item: View) : Holder(item)

}
