package com.cheongseolmo.application.repository.chattingroom

import com.cheongseolmo.domain.chattingroom.entity.ChattingRoom
import com.cheongseolmo.domain.chattingroom.repository.ChattingRoomRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class ChattingRoomRepositoryImpl(
    val jpaRepository: JpaChattingRoomRepository,
): ChattingRoomRepository {
    override fun findByStudyCode(studyCode: String): ChattingRoom? {
        return jpaRepository.findByStudyCode(studyCode)
    }

    override fun save(chattingRoom: ChattingRoom): ChattingRoom {
        return jpaRepository.save(chattingRoom)
    }
}

interface JpaChattingRoomRepository: JpaRepository<ChattingRoom, String> {
    fun findByStudyCode(studyCode: String): ChattingRoom?
}