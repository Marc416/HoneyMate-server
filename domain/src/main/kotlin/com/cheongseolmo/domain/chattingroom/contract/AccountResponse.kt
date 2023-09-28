package com.cheongseolmo.domain.chattingroom.contract

import java.util.*

data class AccountResponse (
    val key: UUID,
    var name: String,
    val email: String,
    val profileImage: String,
)