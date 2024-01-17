package com.marinabits.energietechs.infra.exception;

import com.marinabits.energietechs.infra.exception.error.ErrorCategories;

import java.util.List;

/**
 * Ap used to handle users authentication.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public class ApiException extends RuntimeException {

    private ErrorCategories errorCategory;
    private List<Error> errors;

    /**
     * constructor.
     *
     * @param errorCategory obie error category
     */
    public ApiException(final ErrorCategories errorCategory) {
        this(errorCategory, null, (Throwable) null);
    }

    /**
     * constructor.
     *
     * @param errorCategory obie error category
     */
    public ApiException(final ErrorCategories errorCategory, final Throwable cause) {
        this(errorCategory, null, cause);
    }

    /**
     * constructor.
     *
     * @param errorCategory obie error category
     */
    public ApiException(final ErrorCategories errorCategory, final List<Error> errors) {
        this(errorCategory, errors, (Throwable) null);
    }

    /**
     * constructor.
     *
     * @param errorCategory obie error category
     * @param errors        list of detailed errors
     * @param cause         cause by exception
     */
    public ApiException(final ErrorCategories errorCategory, final List<Error> errors, final Throwable cause) {
        super(errorCategory.getMessage(), cause);
        this.errorCategory = errorCategory;
        this.errors = errors;
    }

    /**
     * constructor.
     *
     * @param errorCategory errorCategory
     * @param errors        errors
     * @param message       message
     */
    public ApiException(final ErrorCategories errorCategory, final List<Error> errors, final String message) {
        super(message);
        this.errorCategory = errorCategory;
        this.errors = errors;
    }

    public ErrorCategories getErrorCategory() {
        return errorCategory;
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {

        return new StringBuilder("ApiException {errorCategory=)")
                .append(errorCategory)
                .append(", errors=")
                .append(errors)
                .append("}")
                .toString();
    }
}