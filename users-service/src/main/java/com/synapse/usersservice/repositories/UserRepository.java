package com.synapse.usersservice.repositories;

import com.synapse.usersservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);

    boolean existsByFirstDocument(String firstDocument);
    boolean existsByFirstDocumentAndIdNot(String firstDocument, String id);

    boolean existsBySecondDocument(String secondDocument);
    boolean existsBySecondDocumentAndIdNot(String secondDocument, String id);

}

