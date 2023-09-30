package com.cheongseolmo.domain.chattingroom.repository

import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom

class FakeChattingRoomRepository :ChattingRoomRepository{
    private val map: MutableMap<Long, ChattingRoom> = mutableMapOf()
    override fun findByStudyCode(studyCode: String): ChattingRoom? {
        map.forEach {
            if(it.value.studyCode==studyCode){
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