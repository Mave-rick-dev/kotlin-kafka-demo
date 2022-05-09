package com.maverick.kotlinkafkademo.request

data class MessageRequest(
    /**
     *  Input Request
     */
    val userId: String,
    val userName: String,
    val messageType: String,
    val channelType: String,

) {
    /**
     *  Message meta-data
     */
    var requestId: String = ""
    var templateId: String = ""
    var priority: String = ""
    var timeOut: String = ""
    var maxRetryPermitted: String = ""
    var encryptionRequired: String = ""
}
