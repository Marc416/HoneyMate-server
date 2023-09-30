package com.cheongseolmo.domain.study.repository.fake

import com.cheongseolmo.domain.account.entity.Account
import com.cheongseolmo.domain.account.repository.AccountRepository
import java.util.*

class FakeAccountRepository : AccountRepository {
    private val map: MutableMap<UUID, Account> = mutableMapOf()
    override fun save(account: Account): Account {
        map[account.key] = account
        return map[account.key]!!
    }

    override fun findByEmail(email: String): Account? {
        return map.values.find { it.email == email }
    }

    override fun findByAccountKey(accountKey: UUID): Account? {
        return map[accountKey]
    }
}