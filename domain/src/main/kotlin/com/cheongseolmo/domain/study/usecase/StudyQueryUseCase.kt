package com.cheongseolmo.domain.study.usecase

import com.cheongseolmo.domain.study.entity.Study

interface StudyQueryUseCase {
    fun findAllStudy(): List<Map<Long, Study>>
    fun findByCode(code: String): Study
}