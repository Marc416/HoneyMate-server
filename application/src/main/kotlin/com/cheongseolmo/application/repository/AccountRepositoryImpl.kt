package com.cheongseolmo.application.repository

import com.cheongseolmo.domain.account.entity.Account
import com.cheongseolmo.domain.account.repository.AccountRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AccountRepositoryImpl(
    private val jpaRepository: AccountRepositoryJpa
) :AccountRepository{
    override fun save(account: Account): Account {
        return jpaRepository.save(account)
    }

    override fun findByEmail(email: String): Account? {
        return jpaRepository.findByEmail(email)
    }
}

interface AccountRepositoryJpa: JpaRepository<Account, UUID> {
    fun findByEmail(email: String): Account?
}

