package com.synapse.usersservice.factories;

import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;
import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.mappers.UserMapper;
import com.synapse.usersservice.messaging.dtos.UserPendingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(CreateUserRequestDTO dto) {
        UserEntity user = userMapper.toCreateEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setStatus(dto.status() != null ? dto.status() : true);
        return user;
    }

    public UserEntity createFromEvent(UserPendingEvent event) {
        UserEntity user = userMapper.toCreateEntityFromEvent(event);
        user.setPassword(passwordEncoder.encode(event.password()));
        user.setStatus(event.status() != null ? event.status() : true);
        return user;
    }
}
