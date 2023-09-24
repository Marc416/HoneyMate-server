package com.cheongseolmo.domain.study.contract.command

import com.cheongseolmo.domain.study.entity.StudyCode
import java.time.ZonedDateTime

data class StudyCodeRead(
    val displayName: String,
    val expiredAt: ZonedDateTime? = null,
){
    companion object{
        fun of(entity: StudyCode): StudyCodeRead{
            return StudyCodeRead(
                displayName = entity.displayName,
                expiredAt = entity.expiredAt,
            )
        }
    }
}