package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.usecase.AccountCreatorUseCase
import com.cheongseolmo.domain.account.usecase.AccountFinderUseCase
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase
import java.util.*

open class StudyFacadeProcessor(
    private val accountFinderUseCase: AccountFinderUseCase,
    private val accountCreatorUseCase: AccountCreatorUseCase,
    private val studyInviteUseCase: StudyInviteUseCase,
) : StudyFacadeUseCase {
    override fun invite(email: String, studyKey: UUID, appLink: String): String {
        if (accountFinderUseCase.findByEmail(email) == null) {
            accountCreatorUseCase.create(email)
        }
        val mobileAppLink = studyInviteUseCase.createAppLink(
            email = email,
            studyKey = studyKey,
            appLink = appLink
        )
        return mobileAppLink
    }


}