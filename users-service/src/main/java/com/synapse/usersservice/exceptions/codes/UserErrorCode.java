package com.synapse.usersservice.exceptions.codes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    EMAIL_ALREADY_EXISTS("USER_001", HttpStatus.CONFLICT, "Email already exists"),
    FIRST_DOCUMENT_ALREADY_EXISTS("USER_002", HttpStatus.CONFLICT, "First document already exists"),
    SECOND_DOCUMENT_ALREADY_EXISTS("USER_003", HttpStatus.CONFLICT, "Second document already exists"),
    USER_NOT_FOUND("USER_004", HttpStatus.NOT_FOUND, "User not found");

    private final String code;
    private final HttpStatus status;
    private final String defaultMessage;

    @Override public String code() { return code; }
    @Override public HttpStatus status() { return status; }
    @Override public String defaultMessage() { return defaultMessage; }
}
