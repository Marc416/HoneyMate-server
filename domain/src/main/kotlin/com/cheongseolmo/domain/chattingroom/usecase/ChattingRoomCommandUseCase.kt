package com.cheongseolmo.domain.chattingroom.usecase

import com.cheongseolmo.domain.chattingroom.contract.ChattingRoomCreateCommand
import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import java.util.*

interface ChattingRoomCommandUseCase {
    fun createRoom(chattingRoomCreateCommand: ChattingRoomCreateCommand):ChattingRoom
    fun enter(accountKey: UUID, studyCode: String): Attendee
    fun out(accountKey: UUID, studyCode: String): Attendee
}