package com.example.simplememo.view.memolist

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.simplememo.R
import com.example.simplememo.common.EventObserver
import com.example.simplememo.databinding.FragmentMemoListBinding
import com.example.simplememo.view.base.BaseFragment

class MemoListFragment : BaseFragment<FragmentMemoListBinding, MemoListViewModel>() {
  override val layoutRes: Int
    get() = R.layout.fragment_memo_list

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun initView(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
    viewModel.getMemoData()
    setSharedTransition()
  }

  override fun subscribeObservers() {
    viewModel.startAddMemo.observe(viewLifecycleOwner, EventObserver {
      startAddMemoFragment(it)
    })

    viewModel.startDetailMemo.observe(viewLifecycleOwner, EventObserver {
      startDetailMemoFragment(it)
    })
  }

  override fun obtainViewModel(): Lazy<MemoListViewModel> = viewModels { viewModelFactory }

  private fun setSharedTransition() {
    postponeEnterTransition()
    viewDataBinding.rvMemoList.doOnPreDraw {
      startPostponedEnterTransition()
    }
  }

  private fun startAddMemoFragment(position: Int) {
    val directions = MemoListFragmentDirections.actionMemoListFragmentToAddMemoFragment(position)
    val view = viewDataBinding.rvMemoList.findViewHolderForAdapterPosition(position)?.itemView

    if (view != null) {
      val transitionName =
        ViewCompat.getTransitionName(view) ?: getString(R.string.transitionName) + "$position"
      val extras = FragmentNavigatorExtras(view to transitionName)
      findNavController().navigate(directions, extras)
    } else {
      findNavController().navigate(directions)
    }
  }

  private fun startDetailMemoFragment(item: Pair<Int, Long>) {
    val directions =
      MemoListFragmentDirections.actionMemoListFragmentToMemoDetailFragment(item.first, item.second)
    val view = viewDataBinding.rvMemoList.findViewHolderForAdapterPosition(item.first)?.itemView

    if (view != null) {
      val transitionName =
        ViewCompat.getTransitionName(view) ?: getString(R.string.transitionName) + "${item.first}"
      val extras = FragmentNavigatorExtras(view to transitionName)
      findNavController().navigate(directions, extras)
    } else {
      findNavController().navigate(directions)
    }
  }


}