package com.cheongseolmo.domain.study.entity

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class StudyCode(
    @ManyToOne(targetEntity = Study::class)
    @JoinColumn(name = "study_id")
    val study: Study,
    val displayName: String,
    val expiredAt: ZonedDateTime? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    val isActive: Boolean
        get() {
            return deletedAt == null && (expiredAt == null || expiredAt.isAfter(ZonedDateTime.now()))
        }

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    var deletedAt: ZonedDateTime? = null
        private set

    fun delete() {
        deletedAt = ZonedDateTime.now()
    }

}