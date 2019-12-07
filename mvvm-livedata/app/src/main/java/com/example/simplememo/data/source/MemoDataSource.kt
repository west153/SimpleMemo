package com.example.simplememo.data.source

import com.example.simplememo.data.dao.MemoDao
import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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

  override fun getMemo(id: Long): Flowable<MemoEntity> {
    return memoDao.findById(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  override fun updateMemo(memo: MemoEntity): Single<Int> {
    return memoDao.update(memo)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  override fun removeMemo(id: Long): Single<Int> {
    return memoDao.deleteById(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

}