package com.example.simplememo.data.mapper

interface Mapper<V, R> {
  fun entityTo(entity: V): R
  fun fromEntity(vo: R): V
}