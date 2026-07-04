package com.synapse.usersservice.exceptions.domain;

import com.synapse.usersservice.exceptions.DomainException;
import com.synapse.usersservice.exceptions.codes.UserErrorCode;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException() {
        super(UserErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
