package com.example.simplememo.view.memodetail

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simplememo.R
import com.example.simplememo.common.EventObserver
import com.example.simplememo.databinding.FragmentMemoDetailBinding
import com.example.simplememo.view.base.BaseFragment

class MemoDetailFragment : BaseFragment<FragmentMemoDetailBinding, MemoDetailViewModel>() {
  override val layoutRes: Int
    get() = R.layout.fragment_memo_detail

  private val args: MemoDetailFragmentArgs by navArgs()
  private lateinit var menu: Menu
  private lateinit var menuInflater: MenuInflater

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementEnterTransition =
      TransitionInflater.from(context).inflateTransition(android.R.transition.move)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    this.menu = menu
    this.menuInflater = inflater
    inflater.inflate(R.menu.menu_detail, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_edit -> setEditMode()
      R.id.action_done -> viewModel.saveMemo(args.memoId)
      R.id.action_delete -> viewModel.removeMemo(args.memoId)
    }
    return super.onOptionsItemSelected(item)
  }


  override fun initView(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
    viewModel.setMemo(args.memoId)
    setSharedTransition()
  }

  override fun subscribeObservers() {
    viewModel.showToast.observe(viewLifecycleOwner, EventObserver {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })

    viewModel.saveMemoComplete.observe(viewLifecycleOwner, EventObserver {
      Toast.makeText(context, R.string.save_complete, Toast.LENGTH_SHORT).show()
      setDetailMode()
    })

    viewModel.removeMemoComplete.observe(viewLifecycleOwner, EventObserver {
      Toast.makeText(context, R.string.remove_complete, Toast.LENGTH_SHORT).show()
      findNavController().navigateUp()
    })
  }

  override fun obtainViewModel(): Lazy<MemoDetailViewModel> = viewModels { viewModelFactory }

  private fun setSharedTransition() {
    postponeEnterTransition()
    viewDataBinding.shared.doOnPreDraw {
      val transitionName = getString(R.string.transitionName) + "${args.position}"
      viewDataBinding.shared.transitionName = transitionName
      startPostponedEnterTransition()
    }
  }

  private fun setEditMode() {
    viewDataBinding.editMemo.apply {
      isEnabled = true
      isFocusable = true
      isFocusableInTouchMode = true
      this.requestFocus()
      this.setSelection(viewDataBinding.editMemo.text.length)
      showKeyboard()
    }
    menu.clear()
    menuInflater.inflate(R.menu.menu_edit, menu)
  }

  private fun setDetailMode() {
    viewDataBinding.editMemo.apply {
      isEnabled = false
      isFocusable = false
      isFocusableInTouchMode = false
    }
    menu.clear()
    menuInflater.inflate(R.menu.menu_detail, menu)
  }

  private fun showKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(viewDataBinding.editMemo, InputMethodManager.SHOW_FORCED)
  }

  private fun hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(viewDataBinding.editMemo.windowToken, 0)
  }

}