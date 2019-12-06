package com.example.simplememo.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simplememo.R
import com.example.simplememo.databinding.ActivityMainBinding
import com.example.simplememo.view.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
  override val layoutRes: Int
    get() = R.layout.activity_main

  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun initViews(savedInstanceState: Bundle?) {
    viewDataBinding.viewModel = viewModel
    setActionBar()
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.nav_host).navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }

  override fun subscribeObservers() {
  }

  override fun getActivityViewModel(): MainViewModel =
    viewModels<MainViewModel> { viewModelFactory }.value

  private fun setActionBar() {
    setSupportActionBar(viewDataBinding.toolbar)
    appBarConfiguration = AppBarConfiguration.Builder(R.id.memoListFragment)
      .build()

    val navController = findNavController(R.id.nav_host)
    setupActionBarWithNavController(navController, appBarConfiguration)

    navController.addOnDestinationChangedListener { _, destination, _ ->
      setToolbarTitle(destination.id)
    }
  }

  private fun setToolbarTitle(id: Int) {
    when (id) {
      R.id.memoListFragment -> getString(R.string.memo_list)
      R.id.addMemoFragment -> getString(R.string.add_memo)
      else -> ""
    }.let {
      supportActionBar?.title = it
    }
  }

}
