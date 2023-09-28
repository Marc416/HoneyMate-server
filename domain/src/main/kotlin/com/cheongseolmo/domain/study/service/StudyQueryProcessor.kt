package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.repository.StudyCodeRepository
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.usecase.StudyQueryUseCase

open class StudyQueryProcessor(
    val studyRepository: StudyRepository,
    val studyCodeRepository: StudyCodeRepository,
) :StudyQueryUseCase{
    override fun findAllStudy(): List<Map<Long, Study>> {
        return studyRepository.findAllStudy()
    }

    override fun findByCode(code: String): Study {
        return studyCodeRepository.findByCode(code).study
    }
}