package com.synapse.usersservice.dtos.rep;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstName,
        String  lastName,
        String firstDocument,
        String secondDocument,
        String email,
        LocalDate birthDate,
        Boolean status,
        UserSummaryDTO createdBy,
        UserSummaryDTO updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public record UserSummaryDTO (UUID id, String firstName, String lastName, String firstDocument, String email ) {}
}
