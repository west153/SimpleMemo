package com.example.simplememo.view.addmemo

import com.example.simplememo.common.ContextLocator
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.view.base.BaseViewModel

class AddMemoViewModel(
  private val memoUseCase: MemoUseCase,
  private val contextLocator: ContextLocator
) : BaseViewModel() {

}