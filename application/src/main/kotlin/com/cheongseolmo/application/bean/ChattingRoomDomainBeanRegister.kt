package com.cheongseolmo.application.bean

import com.cheongseolmo.domain.chattingroom.port.out.AccountFinderPort
import com.cheongseolmo.domain.chattingroom.repository.AttendeeRepository
import com.cheongseolmo.domain.chattingroom.repository.ChattingRoomRepository
import com.cheongseolmo.domain.chattingroom.service.ChattingRoomCommandProcessor
import com.cheongseolmo.domain.chattingroom.service.ChattingRoomQueryProcessor
import org.springframework.stereotype.Service

@Service
class ChattingRoomCommandService(
    chattingRoomRepository: ChattingRoomRepository,
    accountFinderPort: AccountFinderPort,
    attendeeRepository: AttendeeRepository
) : ChattingRoomCommandProcessor(
    chattingRoomRepository = chattingRoomRepository,
    accountFinderPort = accountFinderPort,
    attendeeRepository = attendeeRepository,
)

@Service
class ChattingRoomQueryService(
    chattingRoomRepository: ChattingRoomRepository,
    accountFinderPort: AccountFinderPort,
    attendeeRepository: AttendeeRepository
) : ChattingRoomQueryProcessor(
    chattingRoomRepository = chattingRoomRepository,
    accountFinderPort = accountFinderPort,
    attendeeRepository = attendeeRepository,
)