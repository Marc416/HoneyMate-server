package com.cheongseolmo.domain.account.usecase

import com.cheongseolmo.domain.account.entity.Account

interface AccountCreatorUseCase {
    fun create(email: String, name: String): Account
}