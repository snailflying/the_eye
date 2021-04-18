package com.theone.eye.ui.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.drakeet.multitype.ItemViewBinder
import com.theone.eye.R
import com.theone.framework.ext.dp2px
import com.theone.framework.image.AppImageLoader
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
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
            AppImageLoader.with(cover)
                .load(post.coverResId)
                .placeholder(R.drawable.app_default_img)
                .error(R.drawable.app_default_img)
                .transform(
                  CenterCrop(),
                  RoundedCornersTransformation(dp2px(6f), 0, RoundedCornersTransformation.CornerType.ALL)
                )
                .into(cover)
        }
    }

    /**
     * @author Drakeet Xu
     */
    class Data(var coverResId: String) : Serializable
}
