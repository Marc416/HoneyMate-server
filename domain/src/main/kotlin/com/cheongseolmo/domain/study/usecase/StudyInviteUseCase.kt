package com.cheongseolmo.domain.study.usecase

import java.util.*

interface StudyInviteUseCase {
    fun createAppLink(studyKey: UUID, email: String, appLink: String): String
}