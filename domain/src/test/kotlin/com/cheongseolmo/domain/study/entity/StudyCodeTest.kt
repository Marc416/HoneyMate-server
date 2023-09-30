package com.cheongseolmo.domain.study.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class StudyCodeTest {
    private lateinit var study: Study

    @BeforeEach
    fun setUp() {
        study = Study(
            title = "테스트 스터디",
            spec = StudySpec(
                location = "서초",
                runningTime = "2시간",
                runningPeriod = "3일",
                numberOfRecruits = 1,
            ),
        )

    }


    @Test
    fun `StudyCode 엔티티 생성 테스트 (정상)`(){
        //given
        val displayName = "테스트 코드"
        val expiredAt = ZonedDateTime.now().plusDays(1)

        //when
        val entity = StudyCode(
            study = study,
            displayName = displayName,
            expiredAt = expiredAt,
        )

        //then
        assertThat(entity.study).isEqualTo(study)
        assertThat(entity.displayName).isEqualTo(displayName)
        assertThat(entity.expiredAt).isEqualTo(expiredAt)
        assertThat(entity.isActive).isTrue()
    }

    @Test
    fun `StudyCode 엔티티 생성 테스트 (만료 시간이 지나 만료된 코드)`(){
        //given
        val displayName = "테스트 코드"
        val expiredAt = ZonedDateTime.now().minusDays(1)

        //when
        val entity = StudyCode(
            study = study,
            displayName = displayName,
            expiredAt = expiredAt,
        )

        //then
        assertThat(entity.study).isEqualTo(study)
        assertThat(entity.displayName).isEqualTo(displayName)
        assertThat(entity.expiredAt).isEqualTo(expiredAt)
        assertThat(entity.isActive).isFalse()
    }

    @Test
    fun `StudyCode 엔티티 생성 테스트 (임의로 만료된 코드)`(){
        //given
        val displayName = "테스트 코드"
        val expiredAt = ZonedDateTime.now().plusDays(1)

        //when
        val entity = StudyCode(
            study = study,
            displayName = displayName,
            expiredAt = expiredAt,
        )
        entity.delete()

        //then
        assertThat(entity.study).isEqualTo(study)
        assertThat(entity.displayName).isEqualTo(displayName)
        assertThat(entity.expiredAt).isEqualTo(expiredAt)
        assertThat(entity.isActive).isFalse()
    }
}