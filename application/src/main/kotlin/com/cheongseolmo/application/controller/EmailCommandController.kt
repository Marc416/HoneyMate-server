package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.study.usecase.EmailUseCase
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailCommandController(
    val emailUseCase: EmailUseCase,
) {

// Test로 만들어봤습니다. 다음 피쳐에서 삭제합니다. TODO passwordless auth 구현시
    @Operation(
        tags = ["Study"],
        summary = "스터디 생성",
    )
    @PostMapping("/send")
    fun sendEmail() :Any{
        return emailUseCase.send(
            mailTemplate = MailTemplate(
                title = "스터디 생성",
                body = "스터디가 생성되었습니다.",
                from = "joonheelaert@gmail.com",
                to = "foodiy@naver.com"
            )
        )
    }
}