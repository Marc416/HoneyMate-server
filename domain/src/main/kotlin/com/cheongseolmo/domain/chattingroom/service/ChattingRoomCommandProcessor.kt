package com.cheongseolmo.domain.chattingroom.service

import com.cheongseolmo.domain.chattingroom.contract.ChattingRoomCreateCommand
import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import com.cheongseolmo.domain.chattingroom.port.out.AccountFinderPort
import com.cheongseolmo.domain.chattingroom.repository.AttendeeRepository
import com.cheongseolmo.domain.chattingroom.repository.ChattingRoomRepository
import com.cheongseolmo.domain.chattingroom.usecase.ChattingRoomCommandUseCase
import java.util.*

open class ChattingRoomCommandProcessor(
    private val accountFinderPort: AccountFinderPort,
    private val chattingRoomRepository: ChattingRoomRepository,
    private val attendeeRepository: AttendeeRepository,
) : ChattingRoomCommandUseCase {
    override fun createRoom(chattingRoomCreateCommand: ChattingRoomCreateCommand): ChattingRoom {
        return chattingRoomRepository.save(
            chattingRoom = chattingRoomCreateCommand.toChattingRoom()
        )
    }

    override fun enter(accountKey: UUID, studyCode: String): Attendee {
        val attendee = validatedAttendee(accountKey, studyCode)
        val chattingRoom = chattingRoomRepository.findByStudyCode(studyCode)!!
        chattingRoom.attendees.add(attendee)
        chattingRoomRepository.save(chattingRoom)
        return attendeeRepository.save(attendee)
    }

    override fun out(accountKey: UUID, studyCode: String): Attendee {
        val attendee = validatedAttendee(accountKey, studyCode)
        val chattingRoom = chattingRoomRepository.findByStudyCode(studyCode)!!
        chattingRoom.attendees.minus(attendee)
        chattingRoomRepository.save(chattingRoom)
        attendee.chattingRoom = null
        return attendeeRepository.save(attendee)
    }

    private fun validatedAttendee(accountKey: UUID, studyCode: String): Attendee {
        val chattingRoom = chattingRoomRepository.findByStudyCode(studyCode) ?: throw IllegalArgumentException("존재하는 채팅방이 아닙니다")
        val account = accountFinderPort.findByAccountKey(accountKey = accountKey)?: throw IllegalArgumentException("존재하는 사용자가 아닙니다")
        return Attendee(
            accountKey=accountKey,
            email = account.email,
            chattingRoomKey = chattingRoom
        )
    }
}