package com.synapse.usersservice.exceptions.codes;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String code();
    HttpStatus status();
    String defaultMessage();
}
