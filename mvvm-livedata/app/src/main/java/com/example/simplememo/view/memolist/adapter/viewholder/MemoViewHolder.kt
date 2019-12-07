package com.example.simplememo.view.memolist.adapter.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplememo.databinding.ItemListMemoBinding
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.view.memolist.MemoListViewModel

class MemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val binding = DataBindingUtil.bind<ItemListMemoBinding>(view)
  private val context = view.context

  fun bind(vm: MemoListViewModel, memo: MemoVo) {
    binding?.viewModel = vm
    binding?.position = adapterPosition
    binding?.memo = memo
    binding?.tvContent?.text = memo.content
  }
}