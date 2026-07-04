package com.synapse.usersservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_events")
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedEventEntity {

    @Id
    private String correlationId;

    private LocalDateTime processedAt;

    public ProcessedEventEntity(String correlationId) {
        this.correlationId = correlationId;
        this.processedAt = LocalDateTime.now();
    }
}
