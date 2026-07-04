package com.synapse.usersservice.usecases;

import com.synapse.usersservice.dtos.rep.UserResponseDTO;
import com.synapse.usersservice.exceptions.domain.UserNotFoundExceptionException;
import com.synapse.usersservice.mappers.UserMapper;
import com.synapse.usersservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO execute(UUID id){
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(UserNotFoundExceptionException::new);
    }
}
