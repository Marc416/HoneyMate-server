package com.cheongseolmo.domain.account.repository

import com.cheongseolmo.domain.account.entity.Account

interface AccountRepository {
    fun save(account: Account): Account
    fun findByEmail(email: String): Account?
}