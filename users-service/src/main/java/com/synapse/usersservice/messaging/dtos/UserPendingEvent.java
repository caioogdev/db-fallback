package com.synapse.usersservice.messaging.dtos;

import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;

import java.time.LocalDate;

public record UserPendingEvent(
        String correlationId,
        String firstName,
        String  lastName,
        String firstDocument,
        String secondDocument,
        String email,
        String password,
        String birthDate,
        Boolean status
) {
    public static UserPendingEvent from(CreateUserRequestDTO event, String correlationId) {
        return new UserPendingEvent(
                correlationId,
                event.firstName(),
                event.lastName(),
                event.firstDocument(),
                event.secondDocument(),
                event.email(),
                event.password(),
                event.birthDate().toString(),
                event.status()
        );
    }
}
