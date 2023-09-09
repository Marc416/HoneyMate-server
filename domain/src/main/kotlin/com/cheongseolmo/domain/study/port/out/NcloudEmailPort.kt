package com.cheongseolmo.domain.study.port.out

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.notification.contract.NcloudEmailFeignClientResponse

interface NcloudEmailPort {
    fun send(mailTemplate: MailTemplate): NcloudEmailFeignClientResponse
}