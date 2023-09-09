package com.cheongseolmo.application.port.out

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.notification.contract.NcloudEmailFeignClientResponse
import com.cheongseolmo.domain.study.port.out.NcloudEmailPort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class NcloudEmailPortImpl(
    val ncloudFeignClient: NcloudFeignClient,
    @Value("\${ncloud.accesskey}")
    val accessKey: String,
    @Value("\${ncloud.secretkey}")
    val secretKey: String,
) : NcloudEmailPort {
    private val logger = KotlinLogging.logger {}
    // https://api.ncloud-docs.com/docs/ai-application-service-cloudoutboundmailer-createmailrequest

    var space = " " // one space
    var newLine = "\n" // new line
    var method = "POST" // method
    var url = "/api/v1/mails" // url (include query string)
    var timestamp = "${System.currentTimeMillis()}" // current timestamp (epoch)
    private fun makeSignature(timeStamp: String, method: String, url: String): String {
        val message = StringBuilder()
            .append(method)
            .append(space)
            .append(url)
            .append(newLine)
            .append(timeStamp)
            .append(newLine)
            .append(accessKey)
            .toString();

        val signingKey = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256");
        val mac = Mac.getInstance("HmacSHA256");

        mac.init(signingKey);
        val rawHmac = mac.doFinal(message.toByteArray())
        val encoder: Base64.Encoder = Base64.getEncoder()
        val encodeBase64String = encoder.encodeToString(rawHmac)
        return encodeBase64String;
    }


    override fun send(
        mailTemplate: MailTemplate
    ): NcloudEmailFeignClientResponse {
        try {
            return ncloudFeignClient.send(
                ncpAccessKey = accessKey,
                ncpSignature = makeSignature(timestamp, method, url),
                ncpApiGwTimeStamp = timestamp,
                ncloudEmailRequest = NcloudEmailRequest(
                    senderAddress = mailTemplate.from,
                    senderName = "",
                    title = mailTemplate.title,
                    body = mailTemplate.body,
                    recipients = listOf(
                        NcloudEmailRequest.Recipient(
                            address = mailTemplate.to,
                            name = "",
                            type = "R",
                        )
                    )
                )
            )
        } catch (e: Exception) {
            logger.error { "NcloudEmailPortImpl.send() error: ${e.message}" }
            throw e
        }
    }
}