package com.example.simplememo.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.simplememo.data.entity.MemoEntity

@Dao
interface MemoDao : BaseDao<MemoEntity> {

  @Query("SELECT * FROM Memo")
  fun findByAll(): List<MemoEntity>?

  @Query("DELETE FROM Memo WHERE id = :id")
  fun deleteById(id: Int)

}