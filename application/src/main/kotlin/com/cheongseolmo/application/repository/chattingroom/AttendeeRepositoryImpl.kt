package com.cheongseolmo.application.repository.chattingroom

import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.repository.AttendeeRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AttendeeRepositoryImpl(
    val jpaRepository : JpaAttendeeRepository,
) : AttendeeRepository {
    override fun save(attendee: Attendee): Attendee {
        return jpaRepository.save(attendee)
    }

    override fun findByAccountKey(accountKey: UUID): Attendee? {
        return jpaRepository.findByAccountKey(accountKey)
    }
}

interface JpaAttendeeRepository : JpaRepository<Attendee, UUID> {
    fun findByAccountKey(accountKey: UUID): Attendee?
}