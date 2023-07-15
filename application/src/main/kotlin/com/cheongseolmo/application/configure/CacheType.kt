package com.cheongseolmo.application.configure

enum class CacheType(
    val cacheName: String,
    val expireAfterWrite: Long,
    val maximumSize: Long,
) {
    PRODUCT(
        "product",
        1 * 60000,
        10000,
    ),
    ;
}