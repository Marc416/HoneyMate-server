package com.cheongseolmo.domain.notification.contract

/**
 * property가 빈칸이면 안됩니다.
 */
data class MailTemplate(
    val title: String,
    val body: String,
    val from: String,   // 발신자 이메일
    val to: String,     // 수신자 이메일
)