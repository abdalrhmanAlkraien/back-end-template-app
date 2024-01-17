package com.marinabits.energietechs.infra.exception.error;

import org.springframework.http.HttpStatus;

/**
 * High level error categories.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public enum ErrorCategories {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    MISSING_REQUEST_MANDATORY_FIELD(HttpStatus.BAD_REQUEST, "Missing a mandatory request field"),
    MISSING_REQUEST_HEADER(HttpStatus.BAD_REQUEST, "Missing a mandatory request header"),
    MISSING_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "Missing a request parameter"),
    METHOD_NOT_SUPPORTED(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "Http method is not supported for the requested resource path"),
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Request validation error"),
    BUSINESS_ROLE_ERROR(HttpStatus.BAD_REQUEST, "Business role violation"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");

    private HttpStatus httpStatus;
    private String message;

    /**
     * constructor.
     *
     * @param httpStatus http status
     * @param message    error category level message
     */
    ErrorCategories(final HttpStatus httpStatus, final String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
