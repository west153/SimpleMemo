package com.example.simplememo

import com.example.simplememo.domain.vo.MemoVo

class TestRepository {

  private val memoList = arrayListOf<MemoVo>()

  fun getMemoList(): List<MemoVo> {
    memoList.clear()
    return memoList
  }

//  fun saveMemo(memo: MemoVo): Completable {
//  }
//
//  fun updateMemo(memo: MemoVo): Single<Int> {
//  }
//
//  fun getMemo(id: Long): Flowable<MemoVo> {
//  }
//
//  fun removeMemo(id: Long): Single<Int> {
//  }

}