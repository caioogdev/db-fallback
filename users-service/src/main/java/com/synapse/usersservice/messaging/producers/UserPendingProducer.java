package com.synapse.usersservice.messaging.producers;

import com.synapse.usersservice.messaging.dtos.UserPendingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPendingProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserPending(UserPendingEvent event){
        try {
            kafkaTemplate.send("user-pending", event.correlationId() ,event);
        } catch (Exception e) {
            log.error("Falha no producer: ",e);
            throw new RuntimeException(e);
        }
    }

}
