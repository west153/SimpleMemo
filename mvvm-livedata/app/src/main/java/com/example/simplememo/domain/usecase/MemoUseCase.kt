package com.example.simplememo.domain.usecase

import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class MemoUseCase(private val repository: Repository) {
  fun getMemoList(): Flowable<List<MemoVo>> {
    return repository.getMemoList()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  fun saveMemo(content: String): Completable {
    val memo = MemoVo(content, GregorianCalendar().time.time)
    return repository.saveMemo(memo)
  }

}