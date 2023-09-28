package com.cheongseolmo.application.repository

import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.repository.StudyRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class StudyRepositoryImpl(
    private val jpaRepository: JpaStudyRepository,
):StudyRepository {
    override fun findByKey(studyKey: String): Study {
        return jpaRepository.findByStudyKey(studyKey=studyKey)
    }

    override fun findAllStudy(): List<Map<Long, Study>> {
        return jpaRepository.findAll().map {
            mapOf(it.id to it)
        }

    }

    override fun save(study: Study): Study {
        return jpaRepository.save(study)
    }
}

interface JpaStudyRepository: JpaRepository<Study, Long>{
    override fun findAll(): List<Study>
    fun findByStudyKey(studyKey: String): Study
}