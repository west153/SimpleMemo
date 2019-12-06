package com.example.simplememo.domain

import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable

interface Repository {
  fun getMemoList(): Flowable<List<MemoVo>>
  fun saveMemo(memo: MemoVo): Completable
}