package com.cheongseolmo.application.bean

import com.cheongseolmo.domain.study.repository.StudyRepository
import com.cheongseolmo.domain.study.service.StudyCommandProcessor
import com.cheongseolmo.domain.study.service.StudyQueryProcessor
import org.springframework.stereotype.Service
@Service
class StudyCommandService(
    studyRepository: StudyRepository,
): StudyCommandProcessor (
    studyRepository=studyRepository,
)

@Service
class StudyQueryService(
    studyRepository: StudyRepository,
): StudyQueryProcessor(
    studyRepository=studyRepository,
)