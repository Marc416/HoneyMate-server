package com.cheongseolmo.application.bean

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.account.service.AccountCreatorProcessor
import com.cheongseolmo.domain.account.service.AccountFinderProcessor
import org.springframework.stereotype.Service

@Service
class AccountCreatorService(
    accountRepository: AccountRepository,
) : AccountCreatorProcessor(
    accountRepository = accountRepository
)

@Service
class AccountFinderService(
    accountRepository: AccountRepository,
) : AccountFinderProcessor(
    accountRepository = accountRepository
)