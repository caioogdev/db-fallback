package com.synapse.usersservice.exceptions;

public record FieldViolation(String field, String message, String rejectedValue) {}
