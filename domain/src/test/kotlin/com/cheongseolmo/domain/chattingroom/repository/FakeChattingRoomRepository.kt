package com.cheongseolmo.domain.chattingroom.repository

import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import com.cheongseolmo.domain.study.entity.Study

class FakeChattingRoomRepository :ChattingRoomRepository{
    private val map: MutableMap<Long, ChattingRoom> = mutableMapOf()
    override fun findByStudyKey(studyKey: String): ChattingRoom? {
        map.forEach {
            if(it.value.studyKey==studyKey){
                return it.value
            }
        }
        return null
    }

    override fun save(chattingRoom: ChattingRoom): ChattingRoom {
        map[chattingRoom.id]=chattingRoom
        return map[chattingRoom.id]!!
    }

}