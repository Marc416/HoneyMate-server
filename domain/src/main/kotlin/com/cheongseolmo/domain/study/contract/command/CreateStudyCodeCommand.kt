package com.cheongseolmo.domain.study.contract.command

import com.cheongseolmo.domain.study.entity.Study
import java.time.ZonedDateTime
import java.util.*

data class CreateStudyCodeCommand(
    val studyKey: UUID,
    val displayName: String,

    // 코드의 만료 시간은 있을수도 없을 수도 있습니다.
    val expiredAt: ZonedDateTime? = null,
)