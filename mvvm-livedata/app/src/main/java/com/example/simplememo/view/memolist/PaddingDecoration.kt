package com.example.simplememo.view.memolist

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingDecoration() : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
    outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
  ) {
    outRect.top = 8.dp(view.context)
    if (parent.getChildAdapterPosition(view) % 2 == 0) {
      outRect.left = 8.dp(view.context)
      outRect.right = 4.dp(view.context)
    } else {
      outRect.left = 4.dp(view.context)
      outRect.right = 8.dp(view.context)
    }
  }

  private fun Int.dp(context: Context) = (context.resources.displayMetrics.density * this).toInt()
}