package com.example.simplememo.data.source

import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface DataSource {
  fun getMemoList(): Flowable<List<MemoEntity>>
  fun saveMemo(memo: MemoEntity): Completable
  fun getMemo(id: Long): Flowable<MemoEntity>
  fun updateMemo(memo: MemoEntity): Single<Int>
  fun removeMemo(id: Long): Single<Int>
}