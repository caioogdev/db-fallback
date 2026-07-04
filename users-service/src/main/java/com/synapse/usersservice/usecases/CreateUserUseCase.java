package com.synapse.usersservice.usecases;

import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.factories.UserFactory;
import com.synapse.usersservice.dtos.rep.CreateUserResponseDTO;
import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;
import com.synapse.usersservice.messaging.dtos.UserPendingEvent;
import com.synapse.usersservice.messaging.producers.UserPendingProducer;
import com.synapse.usersservice.repositories.UserRepository;
import com.synapse.usersservice.validators.CreateUserValidator;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final CreateUserValidator createUserValidator;
    private final UserFactory userFactory;
    private final UserPendingProducer userPendingProducer;
    private final Tracer tracer;

    public CreateUserResponseDTO execute(CreateUserRequestDTO dto) {
        createUserValidator.validate(dto);
        UserEntity user = userFactory.create(dto);
        try {
            userRepository.save(user);
            return new CreateUserResponseDTO("User created successfully", user.getId().toString());
        } catch ( JpaSystemException e) {
            String correlationId = tracer.currentSpan() != null
                    ? tracer.currentSpan().context().traceId()
                    : UUID.randomUUID().toString();

            userPendingProducer.publishUserPending(
                    UserPendingEvent.from(dto, correlationId)
            );

            return new CreateUserResponseDTO("User pending, will be processed shortly", correlationId);

        }

    }
}
