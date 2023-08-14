package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase

open class StudyCommandProcessor(
    val studyRepository: StudyRepository,
) :StudyCommandUseCase{
    override fun createStudy(study:Study): Study {
        return studyRepository.save(study)
    }
}