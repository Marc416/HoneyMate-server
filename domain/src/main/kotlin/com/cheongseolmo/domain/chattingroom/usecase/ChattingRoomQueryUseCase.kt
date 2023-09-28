package com.cheongseolmo.domain.chattingroom.usecase

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom

interface ChattingRoomQueryUseCase {
    fun getByStudyKey(studyKey: String): ChattingRoom
    fun findMembersByStudyKey(studyKey: String): MutableSet<Attendee>
}