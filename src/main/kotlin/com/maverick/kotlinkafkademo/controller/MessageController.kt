package com.maverick.kotlinkafkademo.controller

import com.maverick.kotlinkafkademo.request.MessageRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.apache.kafka.server.auditor.AuditEvent;

@RestController
@RequestMapping("/communication/v1")
class MessageController(
    private val kafkaTemplate: KafkaTemplate<String, MessageRequest>,

) {
    @PostMapping("/publish")
    fun publishOTP(@RequestBody messageRequest: MessageRequest): String {
        AuditEvent
        // call metadata-service:
        val future = kafkaTemplate.send("sms", messageRequest)
        println(KafkaHeaders.OFFSET)
       /* future.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onSuccess(result: SendResult<String?, String?>?) {
                println("Producing Message ${messageRequest.messageType} of type ${messageRequest.messageType} to the Topic 'sms' with priority ${messageRequest.priority}")
            }

            override fun onFailure(ex: Throwable) {
                println("Unable to produce Message ${messageRequest.messageType} of type ${messageRequest.messageType} to the Topic 'sms' with priority ${messageRequest.priority}")
            }
        })*/
        return "hello hello hello hello hello"
    }

   /* @PostMapping("/email")
    fun publishSms(@RequestBody emailRequest: EmailRequest): Any {

        // call meta-data service
        return kafkaTemplate.send("email", emailRequest)
    }*/
}
