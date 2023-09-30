package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.usecase.AccountCreatorUseCase
import com.cheongseolmo.domain.account.usecase.AccountFinderUseCase
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase

open class StudyFacadeProcessor(
    private val accountFinderUseCase: AccountFinderUseCase,
    private val accountCreatorUseCase: AccountCreatorUseCase,
    private val studyInviteUseCase: StudyInviteUseCase,
) : StudyFacadeUseCase {
    override fun invite(email: String, name: String, studyCode: String, appLink: String): String {
        if (accountFinderUseCase.findByEmail(email) == null) {
            accountCreatorUseCase.create(email = email, name = name)
        }
        val mobileAppLink = studyInviteUseCase.createAppLink(
            email = email,
            studyCode = studyCode,
            appLink = appLink
        )
        return mobileAppLink
    }


}