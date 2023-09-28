package com.cheongseolmo.domain.chattingroom.service

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import com.cheongseolmo.domain.chattingroom.port.out.AccountFinderPort
import com.cheongseolmo.domain.chattingroom.repository.AttendeeRepository
import com.cheongseolmo.domain.chattingroom.repository.ChattingRoomRepository
import com.cheongseolmo.domain.chattingroom.usecase.ChattingRoomQueryUseCase

open class ChattingRoomQueryProcessor(
    private val accountFinderPort: AccountFinderPort,
    private val chattingRoomRepository: ChattingRoomRepository,
    private val attendeeRepository: AttendeeRepository,

) : ChattingRoomQueryUseCase {
    override fun getByStudyCode(studyCode: String): ChattingRoom {
        return chattingRoomRepository.findByStudyCode(studyCode)?: throw Exception("채팅방이 존재하지 않습니다.")
    }

    override fun findMembersByStudyCode(studyCode: String): MutableSet<Attendee> {
        val chattingRoom = chattingRoomRepository.findByStudyCode(studyCode)

        return chattingRoom?.attendees ?: mutableSetOf()
    }

}