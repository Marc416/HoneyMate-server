package com.cheongseolmo.domain.account.usecase

import com.cheongseolmo.domain.account.entity.Account

interface AccountFinderUseCase {
    fun findByEmail(email: String): Account?
}