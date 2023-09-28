package com.cheongseolmo.domain.study.repository

import com.cheongseolmo.domain.study.entity.Study

interface StudyRepository {
    fun findByKey(studyKey: String): Study
    fun findAllStudy(): List<Map<Long, Study>>
    fun save(study: Study): Study
}