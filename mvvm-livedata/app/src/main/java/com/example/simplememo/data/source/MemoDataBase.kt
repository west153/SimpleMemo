package com.example.simplememo.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplememo.data.dao.MemoDao
import com.example.simplememo.data.entity.MemoEntity

@Database(
  entities = [MemoEntity::class], version = 1
)
abstract class MemoDataBase : RoomDatabase() {
  abstract fun memoDao(): MemoDao
}