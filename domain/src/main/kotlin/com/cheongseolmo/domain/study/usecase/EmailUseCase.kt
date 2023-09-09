package com.cheongseolmo.domain.study.usecase

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.notification.contract.NcloudEmailFeignClientResponse

interface EmailUseCase {
    fun send(mailTemplate: MailTemplate) : NcloudEmailFeignClientResponse
}