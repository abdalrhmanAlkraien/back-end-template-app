package com.marinabits.energietechs.infra.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * This class holds the detailed error occurred while api calling.
 *
 * @author Omar Ismail
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    private String code;
    private String name;
    private String message;

    /**
     * constructor accept the field/header name & message.
     *
     * @param name    field/header name
     * @param message detailed error message
     */
    public ErrorDetails(final String name, final String message) {
        this(null, name, message);
    }

    public ErrorDetails() {
    }

    /**
     * constructor.
     *
     * @param code    error code
     * @param name    field/header name
     * @param message detailed error message
     */
    public ErrorDetails(final String code, final String name, final String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
