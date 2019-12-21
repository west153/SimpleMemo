package com.example.simplememo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`

class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) : ViewAssertion {

  companion object {
    fun withItemCount(count: Int): RecyclerViewItemCountAssertion {
      return withItemCount(`is`(count))
    }

    fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
      return RecyclerViewItemCountAssertion(matcher)
    }
  }

  override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
    if (noViewFoundException != null) {
      throw noViewFoundException
    }
    val recyclerView = view as RecyclerView
    val adapter = recyclerView.adapter
    assertThat(adapter?.itemCount, matcher)
  }
}