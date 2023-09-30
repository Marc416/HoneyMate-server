package com.cheongseolmo.domain.study.usecase

interface StudyInviteUseCase {
    fun createAppLink(studyCode: String, email: String, appLink: String): String
}