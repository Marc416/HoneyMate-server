package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.notification.contract.MailTemplate
import com.cheongseolmo.domain.study.contract.command.CreateStudyCodeCommand
import com.cheongseolmo.domain.study.contract.command.StudyCodeRead
import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudySpec
import com.cheongseolmo.domain.study.service.StudyFacadeUseCase
import com.cheongseolmo.domain.study.usecase.EmailUseCase
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import com.cheongseolmo.domain.study.usecase.StudyQueryUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.view.RedirectView
import java.time.ZonedDateTime
import java.util.*

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
        studyCommandUseCase.createStudy(studyCommand.to(), defaultStudyCode = studyCommand.defaultStudyCode)
    }

    @Operation(
        tags = ["Study"],
        summary = "전체 스터디 조회",
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
        security = [
            SecurityRequirement(name = "Authorization"),
        ],
        tags = ["Study"],
        summary = "특정 스터디의 코드 생성",
    )
    @PostMapping("/{studyKey}/code")
    fun createStudyCode(
        @PathVariable studyKey: UUID,
        @RequestBody request: StudyCodeRequest,
    ): StudyCodeRead {
        return studyCommandUseCase.createCode(
            command = request.toCommand(
                studyKey = studyKey,
            )
        )
    }

    @Operation(
        tags = ["Study"],
        summary = "스터디 단건 조회(코드로 조회)",
    )
    @GetMapping("/{code}")
    fun findStudyByKey(
        @PathVariable code: String,
    ):  StudyResponse {
        return StudyResponse.of(studyQueryUseCase.findByCode(code=code))
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
            name = inviteRequest.name,
            studyCode = inviteRequest.studyCode,
            appLink = inviteRequest.appLink
        )

        // 서버로 다시 redirect 해주기 위해 앱에 입장할 수 있는 링크를 만들어준다.
        val uriComponent = ServletUriComponentsBuilder.fromCurrentRequest().build()
        val hostAddress = "${uriComponent.scheme}://${uriComponent.host}:${uriComponent.port}"
        logging.info { "host: $hostAddress" }
        val redirectUrl = "${hostAddress}/study/join?mobileAppLink=$mobileAppLink"

        val redirectUrlHyperlink = "<HTML><body> <a href=\"${redirectUrl}\">InviteLink</a></body></HTML>"

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
        description = "/invite 에서 보낸 링크를 타고 들어오게 됩니다. /join 에서는 모바일 앱으로 리다이렉트 해줍니다." +
            "ex) ios-or-android-applink-root://join-waiting-room?accountKey=7d522e5e-bdf6-452a-b414-23b9ea62adeb&studyCode=study-code-for-url"
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
    val studyCode: String,
    val name: String,
    val appLink: String     // 모바일에서 자유롭게 앱링크를 만들 수 있도록 프로퍼티로 받도록 합니다.
)

data class StudyResponse(
    val id: Long,
    val key: String,
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
                key = study.key.toString(),
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
    val defaultStudyCode: String? = null,
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

data class StudyCodeRequest(
    val displayName: String,
    // 코드의 만료 시간은 있을수도 없을 수도 있습니다.
    val expiredAt: ZonedDateTime? = null,
) {
    fun toCommand(studyKey: UUID): CreateStudyCodeCommand {
        return CreateStudyCodeCommand(
            studyKey = studyKey,
            displayName = displayName,
            expiredAt = expiredAt,
        )
    }
}