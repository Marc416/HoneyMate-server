package com.cheongseolmo.domain.study.usecase

import com.cheongseolmo.domain.study.entity.Study

interface StudyCommandUseCase {
    fun createStudy(study:Study): Study
}