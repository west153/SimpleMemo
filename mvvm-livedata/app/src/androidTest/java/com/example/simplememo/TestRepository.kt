package com.example.simplememo

import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class TestRepository : Repository {

  private val memoList = arrayListOf<MemoVo>().apply {
    add(MemoVo(1,"1111",0))
    add(MemoVo(2,"2222",0))
    add(MemoVo(3,"3333",0))
  }

  override fun getMemoList(): Flowable<List<MemoVo>> {
    return Flowable.fromArray(memoList)
  }

  override fun saveMemo(memo: MemoVo): Completable {
    memoList.add(memo)
    return Completable.complete()
  }

  override fun updateMemo(memo: MemoVo): Single<Int> {
    memoList.replaceAll { if (it.id == memo.id) memo else it }
    return Single.just(memo.id.toInt())
  }

  override fun getMemo(id: Long): Flowable<MemoVo> {
    return Flowable.just(memoList.first { it.id == id })
  }

  override fun removeMemo(id: Long): Single<Int> {
    memoList.removeIf { it.id == id }
    return Single.just(id.toInt())
  }

  override fun removeAll(): Completable {
    memoList.clear()
    return Completable.complete()
  }

}