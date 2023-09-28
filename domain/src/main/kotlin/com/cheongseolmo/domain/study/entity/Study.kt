package com.cheongseolmo.domain.study.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Study(
    val title: String,
    val subtitle: String = "",
    val studyKey: String,           // 스터디방 코드로 고유키임.

    @Column(columnDefinition = "TEXT")
    val description: String = "",

    @Embedded
    val spec: StudySpec,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    val modifiedAt: ZonedDateTime = ZonedDateTime.now()
}

@Embeddable
class StudySpec(
    val location: String,
    val runningTime: String,
    val runningPeriod: String,
    val numberOfRecruits: Int,
)
