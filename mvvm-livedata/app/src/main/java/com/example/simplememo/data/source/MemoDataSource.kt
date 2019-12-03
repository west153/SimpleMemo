package com.example.simplememo.data.source

import com.example.simplememo.data.dao.MemoDao
import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Flowable

class MemoDataSource(private val memoDao: MemoDao) : DataSource {
  override fun getMemoList(): Flowable<List<MemoEntity>> {
    return memoDao.findByAll()
  }
}