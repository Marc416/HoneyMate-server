package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.study.contract.command.CreateStudyCodeCommand
import com.cheongseolmo.domain.study.contract.command.StudyCodeRead
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudyCode
import com.cheongseolmo.domain.study.exception.NotFoundStudyException
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase
import java.util.*

open class StudyCommandProcessor(
    val studyRepository: StudyRepository,
    val accountRepository: AccountRepository,
) : StudyCommandUseCase, StudyInviteUseCase {
    override fun createStudy(study: Study, defaultStudyCode: String?): Study {
        val study = studyRepository.save(study)
        createCode(CreateStudyCodeCommand(study.key, defaultStudyCode ?: "${study.title}${study.id}"))
        return study
    }

    override fun createCode(command: CreateStudyCodeCommand): StudyCodeRead {
        val study = studyRepository.findByKey(command.studyKey) ?: throw NotFoundStudyException()
        val studyCode = study.createCode(command.displayName)
        studyRepository.save(study)
        return StudyCodeRead.of(studyCode)
    }

    override fun createAppLink(studyKey: UUID, email: String, appLink: String): String {
        // TODO("앱링크결정이 안돼서 임시로 처리함.")
        val account = accountRepository.findByEmail(email) ?: throw Exception("계정이 없습니다")
        return "${appLink}://join-waiting-room?accountKey=${account.key}&studyKey=${studyKey}"
    }
}