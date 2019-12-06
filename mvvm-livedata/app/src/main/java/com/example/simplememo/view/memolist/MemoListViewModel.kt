package com.example.simplememo.view.memolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplememo.common.ContextLocator
import com.example.simplememo.common.Event
import com.example.simplememo.domain.usecase.MemoUseCase
import com.example.simplememo.domain.vo.MemoVo
import com.example.simplememo.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class MemoListViewModel(
  private val memoUseCase: MemoUseCase,
  private val contextLocator: ContextLocator
) : BaseViewModel() {

  private val _memoList = MutableLiveData<List<MemoVo>>()
  val memoList: LiveData<List<MemoVo>> get() = _memoList

  private val _startAddMemo = MutableLiveData<Event<Int>>()
  val startAddMemo: LiveData<Event<Int>> get() = _startAddMemo

  fun getMemoData() {
    memoUseCase.getMemoList()
      .subscribe(this::subscribeMemoList, this::onError)
      .addTo(compositeDisposable)
  }

  fun startAddMemo(position: Int) {
    // shared element transition 버그 때문에 list 클리어후 fragment 이동.
    _memoList.value = arrayListOf()
    _startAddMemo.value = Event(position)
  }

  private fun subscribeMemoList(item: List<MemoVo>) {
    Log.d("memoList", item.toString())
    // TODO Init Memo List
  }

  private fun onError(e: Throwable) {
    e.printStackTrace()
  }

}