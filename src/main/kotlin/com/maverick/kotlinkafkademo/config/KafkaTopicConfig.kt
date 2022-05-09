package com.maverick.kotlinkafkademo.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {

    @Bean
    fun smsTopic(): NewTopic {
        return TopicBuilder
            .name("sms")
            // .replicas(3) ; Default value for prod env
            // .replicas(1)
            .build()
    }

  /*  @Bean
    fun emailTopic(): NewTopic {
        return TopicBuilder.name("email").build()
    }*/
    /*
       @Bean
       fun deadLetterTopic(): NewTopic {
           return TopicBuilder
               .name("dead-letter-queue")
               // .partitions(1)
               // replicas == 1 till production as dev env have only one broker in a cluster
               //      => no other nodes to hold the replicas
               // .replicas(1)
               .build()
       }*/
}
