package com.cheongseolmo.application.port.out

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.chattingroom.contract.AccountResponse
import com.cheongseolmo.domain.chattingroom.port.out.AccountFinderPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountFinderPortImpl(
    val accountRepository: AccountRepository,
) : AccountFinderPort {
    override fun findByAccountKey(accountKey: UUID): AccountResponse? {
        val account = accountRepository.findByAccountKey(
            accountKey = accountKey
        )
        return account?.let {
            AccountResponse(
                key = it.key,
                email = it.email,
                name = it.name,
                profileImage = it.profileImage,
            )
        }
    }
}