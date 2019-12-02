package com.example.simplememo.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.simplememo.R
import com.example.simplememo.databinding.ActivityMainBinding
import com.example.simplememo.view.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
  override val layoutRes: Int
    get() = R.layout.activity_main

  override fun initViews(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
  }

  override fun subscribeObservers() {
  }

  override fun getActivityViewModel(): MainViewModel =
    viewModels<MainViewModel> { viewModelFactory }.value

}
