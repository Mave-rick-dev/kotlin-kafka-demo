package com.maverick.kotlinkafkademo.config

import com.maverick.kotlinkafkademo.request.MessageRequest
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Value("\${spring.kafka.producer.key-serializer}")
    private val keySerializer: String? = null

    @Value("\${spring.kafka.producer.value-serializer}")
    private val valueDeserializer: String? = null

    @Value("\${spring.kafka.producer.acks}")
    private val acks: String? = null

    @Value("\${spring.kafka.producer.batch-size}")
    private val batchSize: String? = null

    @Value("\${spring.kafka.producer.linger.ms}")
    private val lingerMs: String? = null

    @Value("\${spring.kafka.producer.compression-type}")
    private val compressionType: String? = null

    fun producerConfig(): Map<String, Any> {
        val props: HashMap<String, Any> = hashMapOf()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers!!
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = keySerializer!!
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = valueDeserializer!!
     /*   props[ProducerConfig.ACKS_CONFIG] = acks!!
        props[ProducerConfig.BATCH_SIZE_CONFIG] = batchSize!!
        props[ProducerConfig.LINGER_MS_CONFIG] = lingerMs!!
        props[ProducerConfig.COMPRESSION_TYPE_CONFIG] = compressionType!!*/

        return props
    }

    // creates producer instances
    @Bean
    fun producerFactory(): ProducerFactory<String, MessageRequest> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    // sends messages: kafka template
    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, MessageRequest>): KafkaTemplate<String, MessageRequest> {
        return KafkaTemplate(producerFactory)
    }
}
