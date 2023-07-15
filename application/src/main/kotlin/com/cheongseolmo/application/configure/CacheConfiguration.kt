package com.cheongseolmo.application.configure

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cache.caffeine.CaffeineCache
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

@Configuration
class CacheConfiguration {
    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        val caches: List<CaffeineCache> = Arrays.stream(
            CacheType.values()
        )
            .map { cache ->
                CaffeineCache(
                    cache.cacheName, Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cache.expireAfterWrite, TimeUnit.SECONDS)
                        .maximumSize(cache.maximumSize)
                        .build()
                )
            }
            .collect(Collectors.toList())
        cacheManager.setCaches(caches)
        return cacheManager
    }
}