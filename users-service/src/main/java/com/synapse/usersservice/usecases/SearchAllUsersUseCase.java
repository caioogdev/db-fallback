package com.synapse.usersservice.usecases;

import com.synapse.usersservice.dtos.common.PaginationDTO;
import com.synapse.usersservice.dtos.rep.UserResponseDTO;
import com.synapse.usersservice.dtos.req.SearchUserRequestDTO;
import com.synapse.usersservice.entities.UserEntity;
import com.synapse.usersservice.mappers.UserMapper;
import com.synapse.usersservice.repositories.UserRepository;
import com.synapse.usersservice.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchAllUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PaginationDTO<UserResponseDTO> search(SearchUserRequestDTO filters) {
        Pageable pageable = PageRequest.of(filters.page(), filters.perPage());

        Page<UserEntity> page = userRepository.findAll(
                UserSpecification.withFilters(filters), pageable
        );

        return new PaginationDTO<>(
                page.getTotalElements(),
                page.getContent().stream().map(userMapper::toResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
