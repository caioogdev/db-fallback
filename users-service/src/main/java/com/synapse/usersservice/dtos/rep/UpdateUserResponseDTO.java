package com.synapse.usersservice.dtos.rep;

import java.util.UUID;

public record UpdateUserResponseDTO (
    String message,
    UUID id
    ){}
