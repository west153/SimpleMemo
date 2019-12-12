package com.example.simplememo.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MemoDao : BaseDao<MemoEntity> {

  @Query("SELECT * FROM Memo")
  fun findByAll(): Flowable<List<MemoEntity>>

  @Query("SELECT * FROM memo WHERE id = :id")
  fun findById(id: Long): Flowable<MemoEntity>

  @Query("DELETE FROM Memo WHERE id = :id")
  fun deleteById(id: Long): Single<Int>

  @Query("DELETE FROM Memo")
  fun deleteAll(): Completable
}