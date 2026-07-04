package com.synapse.usersservice.messaging.consumers;

import com.synapse.usersservice.entities.ProcessedEventEntity;
import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.factories.UserFactory;
import com.synapse.usersservice.messaging.dtos.UserPendingEvent;
import com.synapse.usersservice.repositories.ProcessedEventRepository;
import com.synapse.usersservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPendingConsumer {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final ProcessedEventRepository processedEventRepository;

    @Transactional
    @KafkaListener(topics = "user-pending", groupId = "users-service-group")
    public void consume(UserPendingEvent event) {
        if (processedEventRepository.existsById(event.correlationId())) {
            log.warn("Evento já processado, ignorando: correlationId={}", event.correlationId());
            return;
        }

        UserEntity user = userFactory.createFromEvent(event);
        userRepository.save(user);
        processedEventRepository.save(new ProcessedEventEntity(event.correlationId()));

        log.info("Usuário criado via Kafka: correlationId={}", event.correlationId());
    }
}