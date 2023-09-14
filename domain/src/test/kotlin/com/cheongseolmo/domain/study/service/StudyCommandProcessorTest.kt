package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudySpec
import com.cheongseolmo.domain.study.repository.fake.FakeStudyRepository
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.repository.fake.FakeAccountRepository
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class StudyCommandProcessorTest {
    lateinit var accountRepository: AccountRepository
    lateinit var studyRepository: StudyRepository
    lateinit var studyCommandProcessor: StudyCommandUseCase

    @BeforeEach
    internal fun setUp() {
        this.studyRepository = FakeStudyRepository()
        this.accountRepository = FakeAccountRepository()
        this.studyCommandProcessor = StudyCommandProcessor(
            studyRepository = studyRepository,
            accountRepository = accountRepository,
        )
    }

    @Test
    fun `study 엔티티 저장`() {
        // Arrange
        val study = Study(
            title = "테스트 타이틀",
            subtitle = "테스트 부 타이틀",
            description = "테스트 설명",
            spec = StudySpec(
                location = "테스트 장소",
                runningTime = "테스트 시간",
                runningPeriod = "테스트 기간",
                numberOfRecruits = 10
            )
        )
        // Action, Assert
        val saveResult = studyCommandProcessor.createStudy(study = study)
        assertEquals(study, saveResult)
    }

    @Test
    fun `study code 생성 테스트`() {
        //given
        val study = Study(
            title = "테스트 타이틀",
            subtitle = "테스트 부 타이틀",
            description = "테스트 설명",
            spec = StudySpec(
                location = "테스트 장소",
                runningTime = "테스트 시간",
                runningPeriod = "테스트 기간",
                numberOfRecruits = 10
            )
        )

        //when
        val displayName = "테스트 코드 이름"
        study.createCode(displayName = displayName)

        //then
        assertThat(study.codes).isNotEmpty
        assertThat(study.codes.size).isEqualTo(1)
        assertThat(study.codes.first().displayName).isEqualTo(displayName)
        assertThat(study.codes.first().isActive).isTrue()
    }
}