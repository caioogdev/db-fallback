package com.synapse.usersservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic userPendingTopic() {
        return TopicBuilder.name("user-pending")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userPendingDltTopic() {
        return TopicBuilder.name("user-pending-dlt")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
