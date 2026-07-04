package com.synapse.usersservice.mappers;

import com.synapse.usersservice.dtos.rep.UserResponseDTO;
import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;
import com.synapse.usersservice.dtos.req.UpdateUserRequestDTO;
import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.messaging.dtos.UserPendingEvent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( componentModel = "spring")
public interface UserMapper {

    UserEntity toCreateEntity(CreateUserRequestDTO dto);
    UserEntity toCreateEntityFromEvent(UserPendingEvent event);
    UserResponseDTO toResponse(UserEntity dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateUserRequestDTO dto, @MappingTarget UserEntity entity);

}
