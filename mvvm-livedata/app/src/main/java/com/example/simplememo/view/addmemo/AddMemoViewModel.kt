package com.example.simplememo.view.addmemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplememo.R
import com.example.simplememo.common.ContextLocator
import com.example.simplememo.common.Event
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class AddMemoViewModel(
  private val memoUseCase: MemoUseCase,
  private val contextLocator: ContextLocator
) : BaseViewModel() {

  val editText = MutableLiveData<String>()

  private val _showToast = MutableLiveData<Event<Int>>()
  val showToast: LiveData<Event<Int>> get() = _showToast

  private val _showSaveComplete = MutableLiveData<Event<Unit>>()
  val showSaveComplete: LiveData<Event<Unit>> get() = _showSaveComplete

  fun saveMemo() {
    if (editText.value.isNullOrBlank()) {
      _showToast.value = Event(R.string.error_memo_empty)
    } else {
      memoUseCase.saveMemo(editText.value ?: return)
        .subscribe({ _showSaveComplete.value = Event(Unit) }, this::onError)
        .addTo(compositeDisposable)
    }
  }

  private fun onError(e: Throwable) {

  }
}