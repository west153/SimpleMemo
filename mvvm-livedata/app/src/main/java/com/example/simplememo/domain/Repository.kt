package com.example.simplememo.domain

import com.example.simplememo.domain.vo.MemoVo
import io.reactivex.Flowable

interface Repository {
  fun getMemoList(): Flowable<List<MemoVo>>
}