package com.synapse.usersservice.exceptions;

import com.synapse.usersservice.exceptions.codes.ErrorCode;
import java.util.List;
import java.util.Objects;

public class DomainException extends RuntimeException {

    private final ErrorCode errorCode;
    private final List<FieldViolation> violations;

    public DomainException(ErrorCode errorCode) {
        this(errorCode, errorCode.defaultMessage(), List.of(), null);
    }

    public DomainException(ErrorCode errorCode, String message, List<FieldViolation> violations, Throwable cause) {
        super(message, cause);
        this.errorCode = Objects.requireNonNull(errorCode);
        this.violations = List.copyOf(Objects.requireNonNullElse(violations, List.of()));
    }

    public ErrorCode errorCode() { return errorCode; }
    public List<FieldViolation> violations() { return violations; }
}
