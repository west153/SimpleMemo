package com.example.simplememo.domain

import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {
  fun getMemoList(): Flowable<List<MemoVo>>
  fun saveMemo(memo: MemoVo): Completable
  fun updateMemo(memo: MemoVo): Single<Int>
  fun getMemo(id: Long): Flowable<MemoVo>
  fun removeMemo(id: Long): Single<Int>
}