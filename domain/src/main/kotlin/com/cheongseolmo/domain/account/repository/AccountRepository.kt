package com.cheongseolmo.domain.account.repository

import com.cheongseolmo.domain.account.entity.Account
import java.util.*

interface AccountRepository {
    fun save(account: Account): Account
    fun findByEmail(email: String): Account?
    fun findByAccountKey(accountKey: UUID): Account?
}