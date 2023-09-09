package com.cheongseolmo.application.bean

import com.cheongseolmo.domain.study.port.out.NcloudEmailPort
import com.cheongseolmo.domain.notification.service.EmailSendProcessor
import org.springframework.stereotype.Service

@Service
class EmailSendService(
    ncloudEmailPort: NcloudEmailPort,
): EmailSendProcessor(
    ncloudEmailPort=ncloudEmailPort,
)