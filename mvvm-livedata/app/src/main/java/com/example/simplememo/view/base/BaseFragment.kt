package com.example.simplememo.view.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.simplememo.SimpleMemoApp
import com.example.simplememo.ViewModelFactory
import com.example.simplememo.common.ContextLocator


abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment() {
  protected val viewModelFactory by lazy { getVmFactory() }
  protected lateinit var viewDataBinding: V
  private lateinit var _viewModel: Lazy<VM>
  protected val viewModel: VM get() = _viewModel.value

  abstract val layoutRes: Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    _viewModel = obtainViewModel()
    viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    setHasOptionsMenu(true)
    return viewDataBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initView(savedInstanceState)
    subscribeObservers()
  }

  abstract fun initView(savedInstanceState: Bundle?)
  abstract fun subscribeObservers()
  abstract fun obtainViewModel(): Lazy<VM>

  private fun getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as SimpleMemoApp).dataRepository
    val contextLocator =
      (activity as? BaseActivity<*, *>)?.getContextLocator() ?: ContextLocator(context)
    return ViewModelFactory(repository, contextLocator)
  }
}