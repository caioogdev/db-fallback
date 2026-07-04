package com.synapse.usersservice.exceptions.domain;

import com.synapse.usersservice.exceptions.DomainException;
import com.synapse.usersservice.exceptions.codes.UserErrorCode;

public class UserNotFoundExceptionException extends DomainException {
    public UserNotFoundExceptionException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}