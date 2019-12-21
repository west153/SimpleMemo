package com.example.simplememo.utils

import com.example.simplememo.RecyclerViewItemCountAssertion
import org.hamcrest.Matchers

fun withItemCount(count: Int): RecyclerViewItemCountAssertion {
  return RecyclerViewItemCountAssertion(Matchers.`is`(count))
}
