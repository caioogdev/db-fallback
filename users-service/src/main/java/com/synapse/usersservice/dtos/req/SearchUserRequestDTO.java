package com.synapse.usersservice.dtos.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record SearchUserRequestDTO(
        String firstName,
        String lastName,
        String email,
        String firstDocument,
        String secondDocument,
        Boolean status,
        LocalDate birthDate,
        Integer page,
        Integer perPage
) {
    public SearchUserRequestDTO {
        if (page == null) page = 0;
        if (perPage == null) perPage = 10;
    }
}