package com.cheongseolmo.domain.study.repository.fake

import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.repository.StudyRepository
import java.util.*

class FakeStudyRepository: StudyRepository {
    private val map: MutableMap<Long, Study> = mutableMapOf()
    override fun findAllStudy(): List<Map<Long, Study>> {
        return map.map { mapOf(it.key to it.value) }
    }

    override fun save(study: Study): Study {
        map[study.id] = study
        return map[study.id]!!
    }

    override fun findByKey(key: UUID): Study? {
        return map.values.find { it.key == key }
    }
}