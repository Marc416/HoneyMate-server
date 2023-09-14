package com.cheongseolmo.domain.study.service

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.study.contract.command.CreateStudyCodeCommand
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.exception.NotFoundStudyException
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase
import java.util.*

open class StudyCommandProcessor(
    val studyRepository: StudyRepository,
    val accountRepository: AccountRepository,
) : StudyCommandUseCase, StudyInviteUseCase {
    override fun createStudy(study: Study): Study {
        return studyRepository.save(study)
    }

    override fun createCode(command: CreateStudyCodeCommand): Study {
        val study = studyRepository.findByKey(command.studyKey) ?: throw NotFoundStudyException()
        study.createCode(command.displayName)
        return studyRepository.save(study)
    }

    override fun createAppLink(studyKey: UUID, email: String, appLink: String): String {
        // TODO("앱링크결정이 안돼서 임시로 처리함.")
        val account = accountRepository.findByEmail(email) ?: throw Exception("계정이 없습니다")
        return "${appLink}://join-waiting-room?accountKey=${account.key}&studyKey=${studyKey}"
    }
}