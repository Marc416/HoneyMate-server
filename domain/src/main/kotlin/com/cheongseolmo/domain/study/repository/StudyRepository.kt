package com.cheongseolmo.domain.study.repository

import com.cheongseolmo.domain.study.entity.Study
import java.util.*

interface StudyRepository {
    fun findAllStudy(): List<Map<Long, Study>>
    fun save(study: Study): Study
    fun findByKey(key: UUID): Study?
}