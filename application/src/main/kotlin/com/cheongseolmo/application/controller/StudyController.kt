package com.cheongseolmo.application.controller

import com.cheongseolmo.domain.study.entity.Study
import com.cheongseolmo.domain.study.entity.StudySpec
import com.cheongseolmo.domain.study.usecase.StudyCommandUseCase
import com.cheongseolmo.domain.study.usecase.StudyQueryUseCase
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.*

@RestController
@RequestMapping("/study")
class StudyController(
    val studyCommandUseCase: StudyCommandUseCase,
    val studyQueryUseCase: StudyQueryUseCase
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
}

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
    ){
        companion object{
            fun of(studySpec:StudySpec):StudySpecResponse{
                return StudySpecResponse(
                    location = studySpec.location,
                    runningTime = studySpec.runningTime,
                    runningPeriod = studySpec.runningPeriod,
                    numberOfRecruits = studySpec.numberOfRecruits,
                )
            }
        }
    }

    companion object{
        fun of(study:Study):StudyResponse{
            return StudyResponse(
                id=study.id,
                title=study.title,
                subtitle=study.subtitle,
                description=study.description,
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