package com.example.simplememo.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplememo.common.Event
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

  protected val compositeDisposable = CompositeDisposable()

  protected val _onBackPressed = MutableLiveData<Event<Unit>>()
  val onBackPressed: LiveData<Event<Unit>> get() = _onBackPressed

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }

  fun getDisposable() = compositeDisposable

  fun onBackPressed() {
    _onBackPressed.value = Event(Unit)
  }

  protected fun <T> MutableLiveData<T>.default(defaultValue: T) = apply { postValue(defaultValue) }
}