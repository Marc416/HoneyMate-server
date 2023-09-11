package com.cheongseolmo.domain.account.service

import com.cheongseolmo.domain.account.entity.Account
import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.account.usecase.AccountFinderUseCase

open class AccountFinderProcessor(
    private val accountRepository: AccountRepository
): AccountFinderUseCase{
    override fun findByEmail(email: String): Account? {
        return accountRepository.findByEmail(email)
    }

}