package com.cheongseolmo.domain.study.repository

import com.cheongseolmo.domain.study.entity.StudyCode

interface StudyCodeRepository {
    fun findByCode(code: String): StudyCode
}