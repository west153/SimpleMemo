package com.example.simplememo.view.memolist

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplememo.R
import com.example.simplememo.databinding.FragmentMemoListBinding
import com.example.simplememo.view.base.BaseFragment

class MemoListFragment : BaseFragment<FragmentMemoListBinding, MemoListViewModel>() {
  override val layoutRes: Int
    get() = R.layout.fragment_memo_list

  override fun initView(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
  }

  override fun addObserver() {
  }

  override fun obtainViewModel(): Lazy<MemoListViewModel> = viewModels { viewModelFactory }
}