package com.cheongseolmo.domain.chattingroom.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    val studyKey: String                     // channelId 로 사용됩니다. studyKey에서 가져옵니다.

    @OneToMany(
        mappedBy = "chattingRoom",
        cascade = [CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE]

    )
    val attendees: MutableSet<Attendee> = mutableSetOf()
    val title: String
    val previewImage: String
    val startAt: ZonedDateTime
    val attendeeLimit: Int

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    val updatedAt: ZonedDateTime
    val deletedAt: ZonedDateTime? = null

    constructor(
        studyKey: String,
        title: String,
        previewImage: String = "",
        startAt: ZonedDateTime,
        attendeeLimit: Int
    ) {
        this.studyKey = studyKey
        this.title = title
        this.previewImage = previewImage
        this.startAt = startAt
        this.attendeeLimit = attendeeLimit
        this.updatedAt = ZonedDateTime.now()
    }
}