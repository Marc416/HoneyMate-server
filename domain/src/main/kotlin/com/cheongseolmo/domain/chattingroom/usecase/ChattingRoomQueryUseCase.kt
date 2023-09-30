package com.cheongseolmo.domain.chattingroom.usecase

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom

interface ChattingRoomQueryUseCase {
    fun getByStudyCode(studyKey: String): ChattingRoom
    fun findMembersByStudyCode(studyCode: String): MutableSet<Attendee>
}