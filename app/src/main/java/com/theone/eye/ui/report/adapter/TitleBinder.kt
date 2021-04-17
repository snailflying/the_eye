package com.theone.eye.ui.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.theone.eye.R
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
class TitleBinder : ItemViewBinder<TitleBinder.Data, TitleBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_title, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Data) {
        holder.title.text = item.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }

    /**
     * @author Drakeet Xu
     */
    class Data(var title: String) : Serializable
}
