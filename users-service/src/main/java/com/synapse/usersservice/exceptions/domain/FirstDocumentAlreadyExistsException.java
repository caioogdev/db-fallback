package com.synapse.usersservice.exceptions.domain;

import com.synapse.usersservice.exceptions.DomainException;
import com.synapse.usersservice.exceptions.codes.UserErrorCode;

public class FirstDocumentAlreadyExistsException extends DomainException {
    public FirstDocumentAlreadyExistsException() {
        super(UserErrorCode.FIRST_DOCUMENT_ALREADY_EXISTS);
    }
}
