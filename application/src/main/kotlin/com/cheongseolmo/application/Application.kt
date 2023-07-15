package com.cheongseolmo.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(
	"com.cheongseolmo.domain",
)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
