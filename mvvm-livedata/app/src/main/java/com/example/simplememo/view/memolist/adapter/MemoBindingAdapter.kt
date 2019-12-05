package com.example.simplememo.view.memolist.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.view.memolist.MemoListViewModel
import com.example.simplememo.view.memolist.PaddingDecoration


@BindingAdapter(value = ["app:item", "app:viewModel"])
fun RecyclerView?.setMemoAdapter(item: List<MemoVo>?, vm: MemoListViewModel) {
  this?.adapter?.run {
    (this as MemoListAdapter).initList(item ?: arrayListOf())
  } ?: run {
    MemoListAdapter(vm).apply {
      this@setMemoAdapter?.adapter = this
      this@setMemoAdapter?.addItemDecoration(PaddingDecoration())
    }
  }
}