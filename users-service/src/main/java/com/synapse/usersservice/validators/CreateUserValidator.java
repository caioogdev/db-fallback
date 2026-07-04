package com.synapse.usersservice.validators;

import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;
import com.synapse.usersservice.exceptions.domain.EmailAlreadyExistsException;
import com.synapse.usersservice.exceptions.domain.FirstDocumentAlreadyExistsException;
import com.synapse.usersservice.exceptions.domain.SecondDocumentAlreadyExistsException;
import com.synapse.usersservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserValidator {

    private final UserRepository userRepository;

    public void validate(CreateUserRequestDTO dto) {

        if( dto.email() != null && userRepository.existsByEmail(dto.email()))
            throw new EmailAlreadyExistsException();

        if( dto.firstDocument() != null && userRepository.existsByFirstDocument(dto.firstDocument()))
            throw new FirstDocumentAlreadyExistsException();

        if( dto.secondDocument() != null && userRepository.existsBySecondDocument(dto.secondDocument()))
            throw new SecondDocumentAlreadyExistsException();

    }


}
