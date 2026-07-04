package com.synapse.usersservice.usecases;

import com.synapse.usersservice.dtos.rep.UpdateUserResponseDTO;
import com.synapse.usersservice.dtos.req.UpdateUserRequestDTO;
import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.exceptions.domain.UserNotFoundExceptionException;
import com.synapse.usersservice.mappers.UserMapper;
import com.synapse.usersservice.repositories.UserRepository;
import com.synapse.usersservice.validators.UpdateUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UpdateUserValidator updateUserValidator;

    public UpdateUserResponseDTO update(UUID id, UpdateUserRequestDTO dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(UserNotFoundExceptionException::new);

        updateUserValidator.validate(dto, id);
        userMapper.updateEntity(dto, entity);
        userRepository.save(entity);

        return new UpdateUserResponseDTO("User updated successfully: ", entity.getId());
    }
}
