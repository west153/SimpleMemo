package com.example.simplememo.data.source

import com.example.simplememo.data.mapper.MemoMapper
import com.example.simplememo.domain.Repository
import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class MemoRepository(
  private val memoDataSource: DataSource,
  private val memoMapper: MemoMapper
) : Repository {
  override fun getMemoList(): Flowable<List<MemoVo>> {
    return memoDataSource.getMemoList()
      .map { it.map(memoMapper::entityToVo) }
  }

  override fun saveMemo(memo: MemoVo): Completable {
    return memoDataSource.saveMemo(memoMapper.voToEntity(memo))
  }

  override fun getMemo(id: Long): Flowable<MemoVo> {
    return memoDataSource.getMemo(id)
      .map { memoMapper.entityToVo(it) }
  }

  override fun updateMemo(memo: MemoVo): Single<Int> {
    return memoDataSource.updateMemo(memoMapper.voToEntity(memo))
  }

  override fun removeMemo(id: Long): Single<Int> {
    return memoDataSource.removeMemo(id)
  }
}