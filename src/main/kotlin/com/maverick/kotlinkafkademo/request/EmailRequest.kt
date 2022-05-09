package com.maverick.kotlinkafkademo.request

data class EmailRequest(
    val userId: String,
    val userName: String,
    val messageType: String,
    val channelType: String
)
