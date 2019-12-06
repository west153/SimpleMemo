package com.example.simplememo.data.source

import com.example.simplememo.data.dao.MemoDao
import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MemoDataSource(private val memoDao: MemoDao) : DataSource {
  override fun getMemoList(): Flowable<List<MemoEntity>> {
    return memoDao.findByAll()
  }

  override fun saveMemo(memo: MemoEntity): Completable {
    return memoDao.insert(memo)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

}