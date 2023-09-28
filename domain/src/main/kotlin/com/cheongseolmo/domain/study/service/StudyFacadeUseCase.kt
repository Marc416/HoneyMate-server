package com.cheongseolmo.domain.study.service

interface StudyFacadeUseCase {
    fun invite(email:String, name: String,  studyCode: String, appLink:String): String
}