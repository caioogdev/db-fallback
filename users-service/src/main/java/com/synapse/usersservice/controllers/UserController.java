package com.synapse.usersservice.controllers;

import com.synapse.usersservice.dtos.common.PaginationDTO;
import com.synapse.usersservice.dtos.rep.CreateUserResponseDTO;
import com.synapse.usersservice.dtos.rep.UpdateUserResponseDTO;
import com.synapse.usersservice.dtos.rep.UserResponseDTO;
import com.synapse.usersservice.dtos.req.CreateUserRequestDTO;
import com.synapse.usersservice.dtos.req.SearchUserRequestDTO;
import com.synapse.usersservice.dtos.req.UpdateUserRequestDTO;
import com.synapse.usersservice.usecases.CreateUserUseCase;
import com.synapse.usersservice.usecases.GetUserUseCase;
import com.synapse.usersservice.usecases.SearchAllUsersUseCase;
import com.synapse.usersservice.usecases.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final SearchAllUsersUseCase searchAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(
            @RequestBody @Valid CreateUserRequestDTO dto
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable  UUID id){
        return ResponseEntity.ok(getUserUseCase.execute(id));
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationDTO<UserResponseDTO>> search(
            @ModelAttribute @Valid SearchUserRequestDTO filters
            ){
        return ResponseEntity.ok(searchAllUsersUseCase.search(filters));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequestDTO dto
    ) {
        return ResponseEntity.ok(updateUserUseCase.update(id, dto));
    }
}
