package com.example.simplememo.data.source

import com.example.simplememo.data.mapper.MemoMapper
import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable

class MemoRepository(
  private val memoDataSource: DataSource,
  private val memoMapper: MemoMapper
) : Repository {
  override fun getMemoList(): Flowable<List<MemoVo>> {
    return memoDataSource.getMemoList()
      .map { it.map(memoMapper::entityTo) }
  }

  override fun saveMemo(memo: MemoVo): Completable {
    return memoDataSource.saveMemo(memoMapper.fromEntity(memo))
  }
}