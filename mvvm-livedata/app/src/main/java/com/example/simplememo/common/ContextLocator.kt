package com.example.simplememo.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat

class ContextLocator(private val context: Context?) {

  fun getString(resId: Int) = context?.getString(resId)

  fun getDisplayWidth() = context?.resources?.displayMetrics?.widthPixels ?: 0
  fun getDisplayHeight() = context?.resources?.displayMetrics?.heightPixels ?: 0
  fun dpToPixel(dp: Float) = (context?.resources?.displayMetrics?.density ?: 4F) * dp


  fun getDimensToPx(resId: Int) = context?.resources?.getDimensionPixelSize(resId) ?: 0
  fun getFont(resId: Int): Typeface? {
    context ?: return null
    return Typeface.create(ResourcesCompat.getFont(context, resId), Typeface.NORMAL)
  }
}