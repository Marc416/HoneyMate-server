package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import com.cheongseolmo.domain.chattingroom.usecase.ChattingRoomQueryUseCase
import io.swagger.v3.oas.annotations.Operation
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import java.util.*


private val logging = KotlinLogging.logger {}

@RestController
@RequestMapping("/chatting-room")
class ChattingRoomQueryController(
    val chattingRoomQueryUseCase: ChattingRoomQueryUseCase,
) {
    @Operation(
        tags = ["ChattingRoom"],
        summary = "채팅룸 조회",
    )
    @GetMapping("/{studyCode}")
    fun getChattingRoom(
        @PathVariable studyCode: String,
    ): ChattingRoomResponse {
        return ChattingRoomResponse.of(chattingRoomQueryUseCase.getByStudyKey(studyCode))
    }

    @Operation(
        tags = ["ChattingRoom"],
        summary = "채팅룸 멤버 조회",
    )
    @GetMapping("/{studyCode}/members")
    fun getChattingRoomMembers(
        @PathVariable studyCode: String,
    ): List<AttendeesResponse> {
        val attendees = chattingRoomQueryUseCase.findMembersByStudyKey(studyCode = studyCode)
        return attendees.map {
            AttendeesResponse.of(it)
        }
    }
}

data class ChattingRoomResponse(
    val studyKey: String,
    val title: String,
    val startAt: String,
    val attendeeLimit: Int,
    val attendeeCount: Int,
) {
    companion object {
        fun of(chattingRoom: ChattingRoom): ChattingRoomResponse {
            return ChattingRoomResponse(
                studyKey = chattingRoom.studyCode,
                title = chattingRoom.title,
                startAt = chattingRoom.startAt.toString(),
                attendeeLimit = chattingRoom.attendeeLimit,
                attendeeCount = chattingRoom.attendees.size,
            )
        }
    }
}

data class AttendeesResponse(
    val accountKey: UUID,
    var name: String,
    val email: String,
    val profileImage: String,
) {
    companion object {
        fun of(attendee: Attendee): AttendeesResponse {
            return AttendeesResponse(
                accountKey = attendee.accountKey,
                name = attendee.name,
                email = attendee.email,
                profileImage = attendee.profileImage,
            )
        }
    }
}