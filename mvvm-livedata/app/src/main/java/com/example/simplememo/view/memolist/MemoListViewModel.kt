package com.example.simplememo.view.memolist

import android.util.Log
import com.example.simplememo.common.ContextLocator
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class MemoListViewModel(
  private val memoUseCase: MemoUseCase,
  private val contextLocator: ContextLocator
) : BaseViewModel() {

  fun getMemoData() {
    memoUseCase.getMemoList()
      .subscribe(this::subscribeMemoList, this::onError)
      .addTo(compositeDisposable)
  }

  private fun subscribeMemoList(item: List<MemoVo>) {
    Log.d("memoList", item.toString())
    if (item.isEmpty()) {
      // TODO show empty view
    } else {
      // TODO Init Memo List
    }
  }

  private fun onError(e: Throwable) {
    e.printStackTrace()
  }

}