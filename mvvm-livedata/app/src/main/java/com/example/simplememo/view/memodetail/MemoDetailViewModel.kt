package com.example.simplememo.view.memodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplememo.R
import com.example.simplememo.common.ContextLocator
import com.example.simplememo.common.Event
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class MemoDetailViewModel(
  private val memoUseCase: MemoUseCase,
  private val contextLocator: ContextLocator
) : BaseViewModel() {
  val editText = MutableLiveData<String>()

  private val _showToast = MutableLiveData<Event<Int>>()
  val showToast: LiveData<Event<Int>> get() = _showToast

  private val _saveMemoComplete = MutableLiveData<Event<Unit>>()
  val saveMemoComplete: LiveData<Event<Unit>> get() = _saveMemoComplete

  private val _removeMemoComplete = MutableLiveData<Event<Unit>>()
  val removeMemoComplete: LiveData<Event<Unit>> get() = _removeMemoComplete

  fun setMemo(memoId: Long) {
    memoUseCase.getMemo(memoId)
      .subscribe({
        editText.value = it.content
      }, this::onError)
      .addTo(compositeDisposable)
  }

  fun saveMemo(memoId: Long) {
    if (editText.value.isNullOrBlank()) {
      _showToast.value = Event(R.string.error_memo_empty)
    } else {
      memoUseCase.updateMemo(memoId, editText.value ?: return)
        .subscribe({ _saveMemoComplete.value = Event(Unit) }, this::onError)
        .addTo(compositeDisposable)
    }
  }

  fun removeMemo(memoId: Long) {
    memoUseCase.removeMemo(memoId)
      .subscribe({ _removeMemoComplete.value = Event(Unit) }, this::onError)
      .addTo(compositeDisposable)
  }

  private fun onError(e: Throwable) {
    e.printStackTrace()
  }
}