package com.cheongseolmo.application.bean

import com.cheongseolmo.domain.account.repository.AccountRepository
import com.cheongseolmo.domain.account.usecase.AccountCreatorUseCase
import com.cheongseolmo.domain.account.usecase.AccountFinderUseCase
import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.service.StudyCommandProcessor
import com.cheongseolmo.domain.study.service.StudyFacadeProcessor
import com.cheongseolmo.domain.study.service.StudyQueryProcessor
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase
import org.springframework.stereotype.Service

@Service
class StudyCommandService(
    studyRepository: StudyRepository,
    accountRepository: AccountRepository,
) : StudyCommandProcessor(
    studyRepository = studyRepository,
    accountRepository = accountRepository,
)

@Service
class StudyQueryService(
    studyRepository: StudyRepository,
) : StudyQueryProcessor(
    studyRepository = studyRepository,
)

@Service
class StudyFacadeService(
    accountFinderUseCase: AccountFinderUseCase,
    accountCreatorUseCase: AccountCreatorUseCase,
    studyInviteUseCase: StudyInviteUseCase,
) : StudyFacadeProcessor(
    accountFinderUseCase = accountFinderUseCase,
    accountCreatorUseCase = accountCreatorUseCase,
    studyInviteUseCase = studyInviteUseCase,
)