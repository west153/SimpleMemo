package com.example.simplememo.data.source

import com.example.simplememo.data.entity.MemoEntity
import io.reactivex.Flowable

interface DataSource {
  fun getMemoList(): Flowable<List<MemoEntity>>
}