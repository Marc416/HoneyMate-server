package com.cheongseolmo.domain.study.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StudyTest {

    @Test
    fun `study 엔티티 생성 테스트`() {
        //given
        val title = "테스트 타이틀"
        val subTitle = "테스트 부 타이틀"
        val description = "테스트 설명"

        val spec = StudySpec(
            location = "테스트 장소",
            runningTime = "테스트 시간",
            runningPeriod = "테스트 기간",
            numberOfRecruits = 10
        )

        //when
        val study = Study(
            title = title,
            subtitle = subTitle,
            description = description,
            spec = spec
        )

        //then
        assertThat(study.title).isEqualTo(title)
        assertThat(study.subtitle).isEqualTo(subTitle)
        assertThat(study.description).isEqualTo(description)
        assertThat(study.spec.location).isEqualTo(spec.location)
        assertThat(study.spec.runningTime).isEqualTo(spec.runningTime)
        assertThat(study.spec.runningPeriod).isEqualTo(spec.runningPeriod)
        assertThat(study.spec.numberOfRecruits).isEqualTo(spec.numberOfRecruits)
    }
}