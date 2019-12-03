package com.example.simplememo.data.mapper

import com.example.simplememo.data.entity.MemoEntity
import com.example.simplememo.domain.vo.MemoVo

class MemoMapper : Mapper<MemoEntity, MemoVo> {

  override fun entityTo(entity: MemoEntity): MemoVo {
    return MemoVo(entity.title, entity.content, entity.date)
  }

  override fun fromEntity(vo: MemoVo): MemoEntity {
    return MemoEntity(title = vo.title, content = vo.content, date = vo.date)
  }
}