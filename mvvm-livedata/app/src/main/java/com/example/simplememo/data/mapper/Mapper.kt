package com.example.simplememo.data.mapper

interface Mapper<V, R> {
  fun entityToVo(entity: V): R
  fun voToEntity(vo: R): V
}