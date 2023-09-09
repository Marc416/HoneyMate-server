package com.cheongseolmo.application.port.out

import com.cheongseolmo.domain.notification.contract.NcloudEmailFeignClientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "Emailer", url = "https://mail.apigw.ntruss.com/api/v1")
interface NcloudFeignClient {
    @PostMapping(value = ["/mails"], consumes = ["application/json"])
    fun send(
        @RequestHeader("x-ncp-iam-access-key") ncpAccessKey: String,
        @RequestHeader("x-ncp-apigw-signature-v2") ncpSignature: String,
        @RequestHeader("x-ncp-apigw-timestamp") ncpApiGwTimeStamp: String,
        @RequestBody ncloudEmailRequest: NcloudEmailRequest,
    ): NcloudEmailFeignClientResponse

}

class NcloudEmailRequest(
    val senderAddress: String,
    val senderName: String,
    val title: String,
    val body: String,
    val recipients: List<Recipient>,
){
    data class Recipient(
        val address: String,
        val name: String,
        val type: String,
    )
}