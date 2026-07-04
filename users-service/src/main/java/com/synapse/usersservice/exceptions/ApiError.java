package com.synapse.usersservice.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        String title,
        int status,
        String detail,
        String instance,
        String code,
        Instant timestamp,
        List<FieldViolation> violations
) {
    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private String instance;
        private String code;
        private Instant timestamp = Instant.now();
        private List<FieldViolation> violations = List.of();

        public Builder title(String v)                    { this.title = v; return this; }
        public Builder status(int v)                      { this.status = v; return this; }
        public Builder detail(String v)                   { this.detail = v; return this; }
        public Builder instance(String v)                 { this.instance = v; return this; }
        public Builder code(String v)                     { this.code = v; return this; }
        public Builder violations(List<FieldViolation> v) {
            this.violations = v == null ? List.of() : List.copyOf(v); return this;
        }

        public ApiError build() { return new ApiError(title, status, detail, instance, code, timestamp, violations); }
    }
}
