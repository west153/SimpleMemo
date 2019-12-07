package com.example.simplememo.data.mapper

import com.example.simplememo.data.entity.MemoEntity
import com.example.simplememo.domain.vo.MemoVo

class MemoMapper : Mapper<MemoEntity, MemoVo> {

  override fun entityToVo(entity: MemoEntity): MemoVo {
    return MemoVo(entity.id, entity.content, entity.date)
  }

  override fun voToEntity(vo: MemoVo): MemoEntity {
    return MemoEntity(vo.id, content = vo.content, date = vo.date)
  }
}