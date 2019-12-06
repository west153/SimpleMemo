package com.example.simplememo.data.source

import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface DataSource {
  fun getMemoList(): Flowable<List<MemoEntity>>
  fun saveMemo(memo: MemoEntity): Completable
}