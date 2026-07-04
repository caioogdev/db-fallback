package com.synapse.usersservice.repositories;

import com.synapse.usersservice.entities.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, String> { }

