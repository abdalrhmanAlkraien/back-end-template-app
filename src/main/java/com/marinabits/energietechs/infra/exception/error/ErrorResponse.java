package com.marinabits.energietechs.infra.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * ENTECH error response structure.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public class ErrorResponse {

    private String code;
    private String id;
    private String message;
    private List<java.lang.Error> errors;

    /**
     * default constructor.
     * keep it private to force use builder.
     */
    ErrorResponse() {

    }

    /**
     * constructor.
     *
     * @param code    error code
     * @param id      error id
     * @param message error general message
     * @param errors  list of actual errors
     */
    private ErrorResponse(final String code, final String id, final String message, final List<java.lang.Error> errors) {
        this.code = code;
        this.id = id;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Error response builder class.
     */
    public static class ErrorResponseBuilder {

        private String code;
        private String id;
        private String message;
        private List<java.lang.Error> errors;

        public ErrorResponseBuilder code(final String code) {
            this.code = code;
            return this;
        }

        public ErrorResponseBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public ErrorResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder errors(final List<java.lang.Error> errors) {
            this.errors = errors;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(code, id, message, errors);
        }
    }

    @NotNull
    @Size(min = 1, max = 40)
    @JsonProperty("Code")
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Size(min = 1, max = 40)
    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 1, max = 500)
    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @JsonProperty("Errors")
    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<java.lang.Error> errors) {
        this.errors = errors;
    }
}
