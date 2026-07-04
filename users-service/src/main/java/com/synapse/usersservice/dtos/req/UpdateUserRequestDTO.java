package com.synapse.usersservice.dtos.req;

import java.time.LocalDate;

public record UpdateUserRequestDTO(
        String firstName,
        String lastName,
        String firstDocument,
        String secondDocument,
        String email,
        String password,
        LocalDate birthDate,
        Boolean status
) {}
