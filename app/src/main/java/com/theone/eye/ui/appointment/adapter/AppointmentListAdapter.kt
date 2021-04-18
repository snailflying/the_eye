package com.theone.eye.ui.appointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theone.eye.databinding.ItemAppointmentBinding
import com.theone.eye.ui.appointment.entity.AppointmentListRes

/**
 * @Author ZhiQiang
 * @Date 4/18/21
 * @Description
 */
class AppointmentListAdapter(private val function: (data: AppointmentListRes?) -> Unit) :
    RecyclerView.Adapter<AppointmentListAdapter.ViewHolder>() {

    private var posts = emptyList<AppointmentListRes>()

    fun setPosts(posts: List<AppointmentListRes>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(function, ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.clickResult = post
        holder.itemViewBinding.tvTitle.text = post.appointName
        holder.itemViewBinding.tvTime.text = post.appointmentTime
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(
        private val function: (data: AppointmentListRes?) -> Unit,
        val itemViewBinding: ItemAppointmentBinding
    ) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        var clickResult: AppointmentListRes? = null

        init {
            itemView.setOnClickListener { v ->
                function.invoke(clickResult)
            }
        }
    }
}
