package com.cheongseolmo.domain.chattingroom.port.out

import com.cheongseolmo.domain.chattingroom.contract.AccountResponse
import java.util.*

interface AccountFinderPort {
    fun findByAccountKey(accountKey: UUID): AccountResponse?
}

