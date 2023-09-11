package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.account.usecase.AccountCreatorUseCase
import com.cheongseolmo.domain.account.usecase.AccountFinderUseCase
import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudySpec
import com.cheongseolmo.domain.study.service.StudyFacadeUseCase
import com.cheongseolmo.domain.study.usecase.EmailUseCase
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import com.cheongseolmo.domain.study.usecase.StudyInviteUseCase
import com.cheongseolmo.domain.study.usecase.StudyQueryUseCase
import io.swagger.v3.oas.annotations.Operation
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.view.RedirectView
import java.util.*
import javax.persistence.*

private val logging = KotlinLogging.logger {}

@RestController
@RequestMapping("/study")
class StudyController(
    val studyCommandUseCase: StudyCommandUseCase,
    val studyQueryUseCase: StudyQueryUseCase,
    val emailUseCase: EmailUseCase,
    val studyFacadeUseCase: StudyFacadeUseCase
) {
    @Operation(
        tags = ["Study"],
        summary = "스터디 생성",
    )
    @PostMapping("")
    fun createStudy(
        @RequestBody studyCommand: StudyCommand,
    ) {
        studyCommandUseCase.createStudy(studyCommand.to())
    }

    @Operation(
        tags = ["Study"],
        summary = "스터디 조회",
    )
    @GetMapping("")
    fun findAllStudy(): List<Map<Long, StudyResponse>> {
        return studyQueryUseCase.findAllStudy().flatMap {
            it.map {
                mapOf(it.key to StudyResponse.of(it.value))
            }
        }
    }

    @Operation(
        tags = ["Study"],
        summary = "인증된 유저에게 스터디방 참가 링크 보내기",
    )
    @PostMapping("/invite")
    fun sendEmail(
        @RequestBody inviteRequest: InviteRequest,
    ): Any {
        val mobileAppLink = studyFacadeUseCase.invite(
            email = inviteRequest.email,
            studyKey = UUID.fromString(inviteRequest.studyKey),
            appLink = inviteRequest.appLink
        )

        // 서버로 다시 redirect 해주기 위해 앱에 입장할 수 있는 링크를 만들어준다.
        val uriComponent = ServletUriComponentsBuilder.fromCurrentRequest().build()
        val hostAddress = "${uriComponent.scheme}://${uriComponent.host}:${uriComponent.port}"
        logging.info { "host: $hostAddress" }
        val redirectUrl = "${hostAddress}/study/join?mobileAppLink=$mobileAppLink"

        val redirectUrlHyperlink ="<HTML><body> <a href=\"${redirectUrl}\">InviteLink</a></body></HTML>"

        return emailUseCase.send(
            mailTemplate = MailTemplate(
                title = "스터디에 초대합니다.",
                body = "스터디참가 링크 :  $redirectUrlHyperlink",
                from = "joonheelaert@gmail.com",
                to = inviteRequest.email
            )
        )
    }

    @Operation(
        tags = ["Study"],
        summary = "스터디 참가링크에서 모바일 앱으로 리다이렉트",
        description = "/invite 에서 보낸 링크를 타고 들어오게 됩니다. /join 에서는 모바일 앱으로 리다이렉트 해줍니다."
    )
    @GetMapping("/join")
    fun join(
        @RequestParam mobileAppLink: String,
    ): RedirectView {
        logging.info { "access mobileAppLink: $mobileAppLink" }
        return RedirectView(mobileAppLink, true)
    }
}

data class InviteRequest(
    val email: String,
    val studyKey: String,
    val appLink: String     // 모바일에서 자유롭게 앱링크를 만들 수 있도록 프로퍼티로 받도록 합니다.
)

data class StudyResponse(
    val id: Long,
    val title: String,
    val subtitle: String,
    val description: String,
    val spec: StudySpecResponse,
    val createdAt: String,
    val modified: String,
) {
    data class StudySpecResponse(
        val location: String,
        val runningTime: String,
        val runningPeriod: String,
        val numberOfRecruits: Int,
    ) {
        companion object {
            fun of(studySpec: StudySpec): StudySpecResponse {
                return StudySpecResponse(
                    location = studySpec.location,
                    runningTime = studySpec.runningTime,
                    runningPeriod = studySpec.runningPeriod,
                    numberOfRecruits = studySpec.numberOfRecruits,
                )
            }
        }
    }

    companion object {
        fun of(study: Study): StudyResponse {
            return StudyResponse(
                id = study.id,
                title = study.title,
                subtitle = study.subtitle,
                description = study.description,
                spec = StudySpecResponse.of(study.spec),
                createdAt = study.createdAt.toString(),
                modified = study.modifiedAt.toString(),
            )
        }
    }
}

data class StudyCommand(
    val title: String,
    val subtitle: String,
    val description: String,
    val spec: StudySpecCommand,
) {
    data class StudySpecCommand(
        val location: String,
        val runningTime: String,
        val runningPeriod: String,
        val numberOfRecruits: Int,
    )

    fun to(): Study {
        return Study(
            title = title,
            subtitle = subtitle,
            description = description,
            spec = StudySpec(
                location = spec.location,
                runningTime = spec.runningTime,
                runningPeriod = spec.runningPeriod,
                numberOfRecruits = spec.numberOfRecruits,
            )
        )
    }
}