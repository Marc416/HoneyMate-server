package com.cheongseolmo.domain.account.service

import com.cheongseolmo.domain.account.entity.Account
import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.account.usecase.AccountCreatorUseCase

open class AccountCreatorProcessor(
    private val accountRepository: AccountRepository
) :AccountCreatorUseCase{
    override fun create(email: String, name: String): Account {
        return accountRepository.save(Account.create(email = email, name = name))
    }
}