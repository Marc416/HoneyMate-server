package com.cheongseolmo.domain.study.usecase

import com.cheongseolmo.domain.study.contract.command.CreateStudyCodeCommand
import com.cheongseolmo.domain.study.contract.command.StudyCodeRead
import com.cheongseolmo.domain.study.entity.Study

interface StudyCommandUseCase {
    fun createStudy(study:Study, defaultStudyCode: String? = null): Study
    fun createCode(command: CreateStudyCodeCommand): StudyCodeRead
}