package com.synapse.usersservice.exceptions.domain;

import com.synapse.usersservice.exceptions.DomainException;
import com.synapse.usersservice.exceptions.codes.UserErrorCode;

public class SecondDocumentAlreadyExistsException extends DomainException {
    public SecondDocumentAlreadyExistsException() {
        super(UserErrorCode.SECOND_DOCUMENT_ALREADY_EXISTS);
    }
}