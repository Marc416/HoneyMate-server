package com.cheongseolmo.domain.notification.service

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.notification.contract.NcloudEmailFeignClientResponse
import com.cheongseolmo.domain.study.port.out.NcloudEmailPort
import com.cheongseolmo.domain.study.usecase.EmailUseCase

open class EmailSendProcessor(
    val ncloudEmailPort: NcloudEmailPort
) : EmailUseCase {

    override fun send(mailTemplate: MailTemplate): NcloudEmailFeignClientResponse {
        return ncloudEmailPort.send(mailTemplate = mailTemplate)
    }
}