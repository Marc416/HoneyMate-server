package com.cheongseolmo.application.repository

import com.cheongseolmo.domain.study.entity.StudyCode
import com.cheongseolmo.domain.study.repository.StudyCodeRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class StudyCodeRepositoryImpl(
    val jpaStudyCodeRepository: JpaStudyCodeRepository,
): StudyCodeRepository {
    override fun findByCode(code: String): StudyCode {
        return jpaStudyCodeRepository.findByDisplayName(code).first()
    }
}

interface JpaStudyCodeRepository:JpaRepository<StudyCode, Long> {
    fun findByDisplayName(code: String): List<StudyCode>
}