package com.cheongseolmo.domain.chattingroom.contract

import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import java.time.ZonedDateTime

data class ChattingRoomCreateCommand(
    val studyCode: String,
    val title: String,
    val startAt: ZonedDateTime,
    val attendeeLimit: Int
){
    fun toChattingRoom():ChattingRoom{
        return ChattingRoom(
            studyKey = studyCode,
            title = title,
            startAt = startAt,
            attendeeLimit = attendeeLimit
        )
    }
}