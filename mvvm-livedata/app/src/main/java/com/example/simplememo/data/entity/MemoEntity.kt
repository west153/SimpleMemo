package com.example.simplememo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Memo")
data class MemoEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val content: String?,
  val date:Long
)