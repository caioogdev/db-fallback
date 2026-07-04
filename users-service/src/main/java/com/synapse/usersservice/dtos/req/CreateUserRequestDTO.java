package com.synapse.usersservice.dtos.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateUserRequestDTO(

        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 100, message = "First name must be between 3 and 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters")
        String  lastName,

        @NotBlank(message = "First document is required")
        @Size(max = 50, message = "First document must have at most 50 characters")
        String firstDocument,

        @Size(max = 50)
        String secondDocument,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 150, message = "Email must have at most 150 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must have at least 8 characters")
        String password,

        @NotNull(message = "Birth date is required")
        LocalDate birthDate,

        Boolean status
) { }
