package com.example.simplememo.view.memolist.adapter.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplememo.databinding.ItemListAddMemoBinding
import com.example.simplememo.view.memolist.MemoListViewModel

class MemoAddViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val binding = DataBindingUtil.bind<ItemListAddMemoBinding>(view)
  private val context = view.context

  fun bind(vm: MemoListViewModel) {
    binding?.viewModel = vm
    binding?.position = adapterPosition
  }
}