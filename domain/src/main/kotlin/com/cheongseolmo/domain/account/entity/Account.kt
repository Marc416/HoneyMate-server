package com.cheongseolmo.domain.account.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account {
    @Id
    @Column(name = "account_key", columnDefinition = "BINARY(16)")
    val key: UUID
    var name: String = ""
    val email: String
    val profileImage: String = ""
    val createdAt: ZonedDateTime
    val modifiedAt: ZonedDateTime
    var deletedAt: ZonedDateTime? = null
        private set
    constructor(
        key: UUID,
        email: String,
        name: String,
    ) {
        this.key = key
        this.email = email
        this.name = name
        this.createdAt = ZonedDateTime.now()
        this.modifiedAt = ZonedDateTime.now()
    }

    companion object{
        fun create(
            key: UUID=UUID.randomUUID(),
            email: String,
            name: String,
        ): Account {
            return Account(
                key = key,
                email = email,
                name = name,
            )
        }
    }

}