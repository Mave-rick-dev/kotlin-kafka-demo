/*
package com.maverick.kotlinkafkademo.config

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.KafkaException
import org.apache.kafka.common.TopicPartition
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.ContainerAwareErrorHandler
import org.springframework.kafka.listener.MessageListenerContainer
import org.springframework.stereotype.Component

@Component
class KafkaDLQConfig(
    private val kafkaTemplate: KafkaTemplate<String, String>
) : ContainerAwareErrorHandler {

    override fun handle(
        thrownException: java.lang.Exception,
        records: MutableList<ConsumerRecord<*, *>>?,
        consumer: Consumer<*, *>,
        container: MessageListenerContainer
    ) {
        val record = records?.get(0)
        try {
            if (record != null) {
                kafkaTemplate.send("dead-letter-queue", record.key() as String,
                    record.value() as String
                )
                consumer.seek(
                    TopicPartition(record.topic(), record.partition()),
                    record.offset() + 1
                )
            }
        } catch (ex: Exception) {
            consumer.seek(TopicPartition(record!!.topic(), record.partition()), record.offset())
            throw KafkaException("Seek to current after exception", thrownException)
        }
    }
}

*/
