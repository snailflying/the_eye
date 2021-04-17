package com.theone.eye.ui.report

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author ZhiQiang
 * @Date 4/14/21
 * @Description
 */
class PostItemDecoration(private val space: Int, private val spanSizeLookup: SpanSizeLookup) : RecyclerView.ItemDecoration() {

    private var threeSize = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)
        outRect.top = space
        if (spanSizeLookup.getSpanSize(position) == 1) {
            threeSize++
            outRect.left = space
            if (threeSize % 3 == 0) {
                outRect.right = space
            }
        } else {
            threeSize = 0
        }
    }
}
