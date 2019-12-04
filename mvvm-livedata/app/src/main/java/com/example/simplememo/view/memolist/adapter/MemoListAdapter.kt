package com.example.simplememo.view.memolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplememo.R
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.view.memolist.MemoListViewModel
import com.example.simplememo.view.memolist.adapter.viewholder.MemoAddViewHolder
import com.example.simplememo.view.memolist.adapter.viewholder.MemoViewHolder

class MemoListAdapter(private val vm: MemoListViewModel) :
  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  companion object {
    const val VIEW_TYPE_ADD = 10
    const val VIEW_TYPE_MEMO = 11
  }

  private val memoList = arrayListOf<MemoVo>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
    when (viewType) {
      VIEW_TYPE_ADD -> MemoAddViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_add_memo, parent,false)
      )
      else -> MemoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent,false)
      )
    }


  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (position) {
      0 -> (holder as MemoAddViewHolder).bind(vm)
      else -> (holder as MemoViewHolder).bind(vm, memoList[position])
    }
  }

  override fun getItemCount(): Int = memoList.size + 1

  override fun getItemViewType(position: Int): Int {
    return if (position == 0) VIEW_TYPE_ADD else VIEW_TYPE_MEMO
  }

  fun initList(item: List<MemoVo>) {
    memoList.clear()
    memoList.addAll(item)
    notifyDataSetChanged()
  }

}