package com.cheongseolmo.domain.chattingroom.repository

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import java.util.*

interface AttendeeRepository {
    fun save(attendee: Attendee): Attendee
    fun findByAccountKey(accountKey: UUID): Attendee?
}