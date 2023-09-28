package com.cheongseolmo.domain.chattingroom.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * Account의 정보를 담고 있는 Entity 참가자로 쓰입니다.
 */
@Entity
class Attendee {
    @Id
    @JoinColumn(name = "account_key", columnDefinition = "BINARY(16)")
    val accountKey: UUID

    @ManyToOne
    @JoinColumn(name = "chatting_room_id")
    var chattingRoom: ChattingRoom? = null       // 채팅을 하지 않는경우 룸에 입장하지 않았기 때문에 null이 될 수 있습니다.
    var name: String = ""
    val email: String
    val profileImage: String = ""

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    val updatedAt: ZonedDateTime

    constructor(
        accountKey: UUID,
        email: String,
        chattingRoomKey: ChattingRoom
    ) {
        this.accountKey = accountKey
        this.email = email
        this.chattingRoom = chattingRoomKey
        this.updatedAt = ZonedDateTime.now()
    }
}