package com.example.simplememo.view.addmemo

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.viewModels
import com.example.simplememo.R
import com.example.simplememo.databinding.FragmentAddMemoBinding
import com.example.simplememo.view.base.BaseFragment

class AddMemoFragment : BaseFragment<FragmentAddMemoBinding, AddMemoViewModel>() {
  override val layoutRes: Int
    get() = R.layout.fragment_add_memo

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementEnterTransition =
      TransitionInflater.from(context).inflateTransition(android.R.transition.move)
  }

  override fun initView(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
    viewDataBinding.shared.transitionName = getString(R.string.transitionName)
  }

  override fun subscribeObservers() {
  }

  override fun obtainViewModel(): Lazy<AddMemoViewModel> = viewModels { viewModelFactory }
}