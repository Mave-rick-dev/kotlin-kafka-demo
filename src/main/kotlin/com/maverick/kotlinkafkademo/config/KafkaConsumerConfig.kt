package com.maverick.kotlinkafkademo.config

import com.maverick.kotlinkafkademo.request.MessageRequest
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Value("\${spring.kafka.consumer.key-deserializer}")
    private val keyDeserializer: String? = null

    @Value("\${spring.kafka.consumer.value-deserialize}")
    private val valueDeserializer: String? = null

    // Bean for more generics
    @Bean
    fun consumerConfig(): Map<String, Any> {
        val props: HashMap<String, Any> = hashMapOf()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers!!
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = keyDeserializer!!
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = valueDeserializer!!
        props[ConsumerConfig.GROUP_ID_CONFIG] = "group-id"
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        props[JsonDeserializer.VALUE_DEFAULT_TYPE] = "com.maverick.kotlinkafkademo.request.MessageRequest"

        return props
    }
    @Bean
    fun consumerFactory(): ConsumerFactory<String, MessageRequest> {
        return DefaultKafkaConsumerFactory(consumerConfig())
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageRequest>> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, MessageRequest> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
