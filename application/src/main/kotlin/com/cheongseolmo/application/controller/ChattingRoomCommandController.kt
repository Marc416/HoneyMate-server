package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.chattingroom.contract.ChattingRoomCreateCommand
import com.cheongseolmo.domain.chattingroom.usecase.ChattingRoomCommandUseCase
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import java.util.*

@RestController
@RequestMapping("/chatting-room")
class ChattingRoomCommandController(
    val chattingRoomCommandUseCase: ChattingRoomCommandUseCase,
) {
    @Operation(
        tags = ["ChattingRoom"],
        summary = "채팅룸 생성",
    )
    @PostMapping("")
    fun createChattingRoom(
        @RequestBody studyCommand: ChattingRoomCreateRequest,
    ) {
        chattingRoomCommandUseCase.createRoom(studyCommand.toCommand())
    }

    @Operation(
        tags = ["ChattingRoom"],
        summary = "채팅룸 입장",
    )
    @PostMapping("/enter")
    fun enterChattingRoom(
        @RequestBody enterOrOutRequest: EnterOrOutRequest,
    ) {
        chattingRoomCommandUseCase.enter(
            accountKey = UUID.fromString(enterOrOutRequest.accountKey),
            studyCode = enterOrOutRequest.studyCode
        )
    }

    @Operation(
        tags = ["ChattingRoom"],
        summary = "채팅룸 퇴장",
    )
    @PostMapping("/out")
    fun outChattingRoom(
        @RequestBody enterOrOutRequest: EnterOrOutRequest,
    ) {
        chattingRoomCommandUseCase.out(
            accountKey = UUID.fromString(enterOrOutRequest.accountKey),
            studyCode = enterOrOutRequest.studyCode
        )
    }
}

data class ChattingRoomCreateRequest(
    val studyCode: String,
    val title: String,
    val startAt: ZonedDateTime,
    val attendeeLimit: Int
) {
    fun toCommand(): ChattingRoomCreateCommand {
        return ChattingRoomCreateCommand(
            studyCode = studyCode,
            title = title,
            startAt = startAt,
            attendeeLimit = attendeeLimit
        )
    }
}

data class EnterOrOutRequest(
    val accountKey: String,
    val studyCode: String,
)