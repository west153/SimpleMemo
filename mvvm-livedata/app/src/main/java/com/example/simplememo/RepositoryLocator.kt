package com.example.simplememo

import android.content.Context
import androidx.room.Room
import com.example.simplememo.data.mapper.MemoMapper
import com.example.simplememo.data.source.DataSource
import com.example.simplememo.data.source.MemoDataBase
import com.example.simplememo.data.source.MemoDataSource
import com.example.simplememo.data.source.MemoRepository
import com.example.simplememo.domain.Repository


object RepositoryLocator {

  private var database: MemoDataBase? = null
  @Volatile
  var memoRepository: Repository? = null

  fun provideRepository(context: Context): Repository {
    synchronized(this) {
      return memoRepository ?: memoRepository ?: createRepository(context)
    }
  }

  private fun createRepository(context: Context): Repository {
    val repository = MemoRepository(createDataSource(context), MemoMapper())
    memoRepository = repository
    return repository
  }

  private fun createDataSource(context: Context): DataSource {
    val database = database ?: createDataBase(context)
    return MemoDataSource(database.memoDao())
  }

  private fun createDataBase(context: Context): MemoDataBase {
    val db = Room.databaseBuilder(
      context.applicationContext,
      MemoDataBase::class.java, "memo.db"
    )
      .build()
    database = db
    return db
  }

  fun close() {
    database?.close()
    database = null
    memoRepository = null
  }
}