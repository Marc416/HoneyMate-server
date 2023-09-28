package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudySpec
import com.cheongseolmo.domain.study.repository.FakeStudyRepository
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID


class StudyCommandProcessorTest {
    lateinit var studyRepository: StudyRepository
    lateinit var accountRepository: AccountRepository
    lateinit var studyCommandProcessor: StudyCommandUseCase

    @BeforeEach
    internal fun setUp() {
        this.studyRepository = FakeStudyRepository()
        this.accountRepository = mockk()
        this.studyCommandProcessor = StudyCommandProcessor(
            studyRepository=studyRepository,
            accountRepository=accountRepository
        )

    }

    @Test
    fun `study 엔티티 저장`() {
        // Arrange
        val study = Study(
            studyKey = UUID.randomUUID().toString(),
            title = "테스트 타이틀",
            subtitle = "테스트 부 타이틀",
            description = "테스트 설명",
            spec = StudySpec(
                location = "테스트 장소",
                runningTime = "테스트 시간",
                runningPeriod = "테스트 기간",
                numberOfRecruits = 10,
            )
        )
        // Action, Assert
        val saveResult = studyCommandProcessor.createStudy(study=study)
        assertEquals(study, saveResult)
    }
}