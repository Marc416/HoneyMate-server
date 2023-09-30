package com.cheongseolmo.domain.chattingroom.service

import com.cheongseolmo.domain.chattingroom.contract.ChattingRoomCreateCommand
import com.cheongseolmo.domain.chattingroom.entity.Attendee
import com.cheongseolmo.domain.chattingroom.port.out.FakeAccountFinderPortImpl
import com.cheongseolmo.domain.chattingroom.repository.AttendeeRepository
import com.cheongseolmo.domain.chattingroom.repository.ChattingRoomRepository
import com.cheongseolmo.domain.chattingroom.repository.FakeAttendeeRepository
import com.cheongseolmo.domain.chattingroom.repository.FakeChattingRoomRepository
import com.cheongseolmo.domain.chattingroom.usecase.ChattingRoomCommandUseCase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import java.util.*

class ChattingRoomCommandProcessorTest {
    lateinit var chattingRoomCommandUseCommandUseCase: ChattingRoomCommandUseCase
    lateinit var chattingRoomRepository: ChattingRoomRepository
    lateinit var attendeeRepository: AttendeeRepository

    lateinit var accountFinderPort: FakeAccountFinderPortImpl

    @BeforeEach
    fun setUp() {

        this.chattingRoomRepository = FakeChattingRoomRepository()
        this.attendeeRepository = FakeAttendeeRepository()
        this.accountFinderPort = FakeAccountFinderPortImpl()

        chattingRoomCommandUseCommandUseCase = ChattingRoomCommandProcessor(
            accountFinderPort = accountFinderPort,
            chattingRoomRepository = chattingRoomRepository,
            attendeeRepository = attendeeRepository

        )
    }

    @Test
    fun `채팅룸을 생성할 수 있다`() {
        // Arrange, Action
        val chattingRoomCommand = ChattingRoomCreateCommand(
            studyCode = "studyKey",
            title = "title",
            startAt = ZonedDateTime.now(),
            attendeeLimit = 10
        )
        val createdRoom = chattingRoomCommandUseCommandUseCase.createRoom(
            chattingRoomCommand
        )
        val chattingRoom = chattingRoomRepository.findByStudyCode("studyKey")

        // Assert
        assertThat(createdRoom.id).isEqualTo(chattingRoom?.id)
    }

    @Test
    fun `채팅룸에 입장(채팅룸이 없는경우의 예외 발생)`() {
        // Arrange
        val accountKey = UUID.randomUUID()
        val channelId = "studyKey"
        // Action, Assert
        assertThatThrownBy {
            chattingRoomCommandUseCommandUseCase.enter(
                accountKey = accountKey,
                studyCode = channelId
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `채팅룸에 입장(정상 진입)`() {
        // Arrange
        val accountKey = UUID.randomUUID()
        val studyKey = "studyKey"
        // 모임 생성
        val chattingRoomCommand = ChattingRoomCreateCommand(
            studyCode = studyKey,
            title = "title",
            startAt = ZonedDateTime.now(),
            attendeeLimit = 10
        )
        val chattingRoom = chattingRoomCommandUseCommandUseCase.createRoom(
            chattingRoomCommand
        )
        // 모임에 신청한 Attendee 생성
        val attnder = Attendee(
            accountKey = accountKey,
            email = "email@email",
           chattingRoomKey = chattingRoom
        )
        attendeeRepository.save(attnder)
        accountFinderPort.save(attnder)

        // Action
        // 채팅방 입장
        val attendee = chattingRoomCommandUseCommandUseCase.enter(
            accountKey = accountKey,
            studyCode = studyKey
        )
        val chattingRoomEntered = chattingRoomRepository.findByStudyCode(studyKey)


        // Assert
        assertThat(attendee.chattingRoom?.studyCode).isEqualTo(studyKey)
        assertThat(chattingRoomEntered?.attendees?.size).isEqualTo(1)

    }

}