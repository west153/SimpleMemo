package com.example.simplememo.view.addmemo

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
import com.example.simplememo.databinding.FragmentAddMemoBinding
import com.example.simplememo.view.base.BaseFragment

class AddMemoFragment : BaseFragment<FragmentAddMemoBinding, AddMemoViewModel>() {
  override val layoutRes: Int
    get() = R.layout.fragment_add_memo

  private val args: AddMemoFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedElementEnterTransition =
      TransitionInflater.from(context).inflateTransition(android.R.transition.move)
  }


  override fun initView(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
    setSharedTransition()
  }


  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_add, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_done) {
      viewModel.saveMemo()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun subscribeObservers() {

    viewModel.showToast.observe(viewLifecycleOwner, EventObserver {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })

    viewModel.showSaveComplete.observe(viewLifecycleOwner, EventObserver {
      saveComplete()
    })
  }

  override fun obtainViewModel(): Lazy<AddMemoViewModel> = viewModels { viewModelFactory }

  private fun setSharedTransition() {
    postponeEnterTransition()
    viewDataBinding.shared.doOnPreDraw {
      viewDataBinding.shared.transitionName =
        getString(R.string.transitionName) + "${args.position}"
      startPostponedEnterTransition()
    }
  }

  private fun saveComplete() {
    Toast.makeText(context, R.string.save_complete, Toast.LENGTH_SHORT).show()
    hideKeyboard()
    findNavController().navigateUp()
  }

  private fun hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(viewDataBinding.editMemo.windowToken, 0)
  }

}