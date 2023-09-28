package com.cheongseolmo.domain.chattingroom.repository

import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom

interface ChattingRoomRepository {
    fun findByStudyKey(studyKey: String): ChattingRoom?
    fun save(chattingRoom: ChattingRoom): ChattingRoom
}