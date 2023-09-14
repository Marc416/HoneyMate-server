package com.cheongseolmo.domain.study.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
class Study(
    val title: String,
    val subtitle: String = "",

    @Column(columnDefinition = "TEXT")
    val description: String = "",

    @Embedded
    val spec: StudySpec,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    // study key랑 아고라 channel id로 이용
    @Column(name = "entity_key", columnDefinition = "BINARY(16)")
    val key: UUID = UUID.randomUUID()

    @OneToMany(mappedBy = "study", cascade = [CascadeType.ALL], orphanRemoval = true)
    // 이거 MutableList로 사용하지 말고 List로 변경 해야 합니다.
    // backing field 같은거 이용해서 해야되는데... 일단은 이렇게 해놓고 나중에 수정하겠습니다.
    val codes: MutableList<StudyCode> = mutableListOf()

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    val modifiedAt: ZonedDateTime = ZonedDateTime.now()

    fun createCode(displayName: String, expiredAt: ZonedDateTime? = null): StudyCode {
        val studyCode = StudyCode(
            study = this,
            displayName = displayName,
            expiredAt = expiredAt,
        )
        codes.add(studyCode)
        return studyCode
    }
}

@Embeddable
class StudySpec(
    val location: String,
    val runningTime: String,
    val runningPeriod: String,
    val numberOfRecruits: Int,
)
