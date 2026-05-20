package com.example.beforemerge.ui.auth

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val column = position % spanCount

        outRect.left = spacing * column / spanCount
        outRect.right = spacing * (spanCount - 1 - column) / spanCount

        if (position >= spanCount) {
            outRect.top = spacing
        }
    }
}