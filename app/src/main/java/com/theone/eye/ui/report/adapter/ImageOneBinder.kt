package com.theone.eye.ui.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.theone.eye.R
import java.io.Serializable

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
class ImageOneBinder : ItemViewBinder<ImageOneBinder.Data, ImageOneBinder.ViewHolder>() {

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.item_image, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Data) {
    holder.setData(item)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cover: ImageView = itemView.findViewById(R.id.image)

    fun setData(post: Data) {
      cover.setImageResource(post.coverResId)
    }
  }

  /**
   * @author Drakeet Xu
   */
  class Data(var coverResId: Int):Serializable
}
