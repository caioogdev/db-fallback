package com.synapse.usersservice.validators;

import com.synapse.usersservice.dtos.req.UpdateUserRequestDTO;
import com.synapse.usersservice.exceptions.domain.EmailAlreadyExistsException;
import com.synapse.usersservice.exceptions.domain.FirstDocumentAlreadyExistsException;
import com.synapse.usersservice.exceptions.domain.SecondDocumentAlreadyExistsException;
import com.synapse.usersservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateUserValidator {

    private final UserRepository userRepository;

    public void validate(UpdateUserRequestDTO dto, UUID id) {
        if (dto.email() != null && userRepository.existsByEmailAndIdNot(dto.email(), id))
            throw new EmailAlreadyExistsException();

        if (dto.firstDocument() != null && userRepository.existsByFirstDocumentAndIdNot(dto.firstDocument(), id.toString()))
            throw new FirstDocumentAlreadyExistsException();

        if (dto.secondDocument() != null && userRepository.existsBySecondDocumentAndIdNot(dto.secondDocument(), id.toString()))
            throw new SecondDocumentAlreadyExistsException();
    }
}
