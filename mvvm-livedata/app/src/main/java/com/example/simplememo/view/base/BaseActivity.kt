package com.example.simplememo.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.simplememo.SimpleMemoApp
import com.example.simplememo.ViewModelFactory
import com.example.simplememo.common.ContextLocator


abstract class BaseActivity<V : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

  protected val viewModelFactory by lazy { getVmFactory() }
  protected lateinit var viewDataBinding: V
  protected lateinit var viewModel: VM
  protected abstract val layoutRes: Int
  private var contextLocator: ContextLocator? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewDataBinding = DataBindingUtil.setContentView(this, layoutRes)
    viewModel = getActivityViewModel()
    initViews(savedInstanceState)
    subscribeObservers()
    viewDataBinding.lifecycleOwner = this
  }

  abstract fun initViews(savedInstanceState: Bundle?)
  abstract fun subscribeObservers()
  abstract fun getActivityViewModel(): VM
  fun getContextLocator() = contextLocator ?: ContextLocator(this)

  private fun getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as SimpleMemoApp).dataRepository
    contextLocator = ContextLocator(this)
    return ViewModelFactory(repository, contextLocator!!)
  }
}