package org.sopt.Seminar.global.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtValidationType {
    VALID_JWT("VALID_JWT"),
    INVALID_JWT_TOKEN("INVALID_JWT_TOKEN"),
    EXPIRED_JWT_TOKEN("EXPIRED_JWT_TOKEN"),
    UNSUPPORTED_JWT_TOKEN("UNSUPPORTED_JWT_TOKEN"),
    EMPTY_JWT("EMPTY_JWT");

    private final String type;
}
