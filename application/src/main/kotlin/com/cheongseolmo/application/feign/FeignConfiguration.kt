package com.cheongseolmo.application.feign

import feign.Logger
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.cheongseolmo.application"])
class FeignConfiguration(
) {
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return feign.Logger.Level.FULL
    }
}