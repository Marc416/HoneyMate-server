package com.cheongseolmo.domain.study.service

import java.util.*

interface StudyFacadeUseCase {
    fun invite(email:String, studyKey: UUID, appLink:String): String
}