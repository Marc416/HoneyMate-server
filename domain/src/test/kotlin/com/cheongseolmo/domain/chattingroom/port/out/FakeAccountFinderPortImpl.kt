package com.cheongseolmo.domain.chattingroom.port.out

import com.cheongseolmo.domain.chattingroom.contract.AccountResponse
import com.cheongseolmo.domain.chattingroom.entity.Attendee
import java.util.*

class FakeAccountFinderPortImpl : AccountFinderPort {
    val map = mutableMapOf<UUID, Attendee>()
    override fun findByAccountKey(accountKey: UUID): AccountResponse? {
        val attendee = map[accountKey]
        return attendee?.let {
            AccountResponse(
                key = it.accountKey,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage,
            )
        }
    }

    fun save(attendee: Attendee): Attendee {
        map[attendee.accountKey] = attendee
        return attendee
    }
}