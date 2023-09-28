package com.cheongseolmo.domain.chattingroom.repository

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import java.util.*

class FakeAttendeeRepository : AttendeeRepository{
    private val map: MutableMap<UUID, Attendee> = mutableMapOf()
    override fun save(attendee: Attendee): Attendee {
        map[attendee.accountKey]=attendee
        return attendee
    }

    override fun findByAccountKey(accountKey: UUID): Attendee? {
        map.forEach {
            if(it.value.accountKey == accountKey){
                return it.value
            }
        }
        return null
    }

}