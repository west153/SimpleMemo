package com.example.simplememo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplememo.common.ContextLocator
import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.view.addmemo.AddMemoViewModel
import com.example.simplememo.view.main.MainViewModel
import com.example.simplememo.view.memolist.MemoListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
  private val repository: Repository,
  private val contextLocator: ContextLocator
) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    with(modelClass) {
      when {
        isAssignableFrom(MainViewModel::class.java) ->
          MainViewModel(MemoUseCase(repository), contextLocator)
        isAssignableFrom(MemoListViewModel::class.java) ->
          MemoListViewModel(MemoUseCase(repository), contextLocator)
        isAssignableFrom(AddMemoViewModel::class.java) ->
          AddMemoViewModel(MemoUseCase(repository), contextLocator)
        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
      }
    } as T
}