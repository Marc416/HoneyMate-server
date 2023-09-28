package com.cheongseolmo.domain.study.repository

import com.cheongseolmo.domain.study.entity.Study

class FakeStudyRepository: StudyRepository {
    private val map: MutableMap<Long, Study> = mutableMapOf()
    override fun findByKey(studyKey: String): Study {
        TODO("Not yet implemented")
    }

    override fun findAllStudy(): List<Map<Long, Study>> {
        return map.map { mapOf(it.key to it.value) }
    }

    override fun save(study: Study): Study {
        map[study.id] = study
        return map[study.id]!!
    }
}