package com.maverick.kotlinkafkademo.config

import com.maverick.kotlinkafkademo.request.MessageRequest
import org.springframework.kafka.annotation.DltHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Component

@Component
class KafkaListeners {


    @RetryableTopic(
        attempts = "3",
        backoff = Backoff(delay = 1000, multiplier = 2.0),
        autoCreateTopics = "false",
        topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
        topics = ["sms"]
    )
    fun smsListener(conRequest: MessageRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {
        println("Message is processing...")
        println("hello hello ${conRequest.messageType} from $topic") // throw RuntimeException("SMS could not be sent!!")
        println("Message processing complete!!")
    }

    @DltHandler
    fun dlt(conRequest: MessageRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {

        println(KafkaHeaders.DLT_EXCEPTION_CAUSE_FQCN)
        println("${conRequest.messageType} from $topic")
    }
}
