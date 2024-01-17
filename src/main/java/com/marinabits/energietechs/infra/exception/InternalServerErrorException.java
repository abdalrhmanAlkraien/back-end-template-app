package com.marinabits.energietechs.infra.exception;


import com.marinabits.energietechs.infra.exception.error.ErrorCategories;

/**
 * Internal server error exception.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public class InternalServerErrorException extends ApiException {

    /**
     * constructor.
     */
    public InternalServerErrorException() {
        super(ErrorCategories.INTERNAL_SERVER_ERROR);
    }

    /**
     * constructor.
     */
    public InternalServerErrorException(final String message) {
        super(ErrorCategories.INTERNAL_SERVER_ERROR, null, message);
    }

    /**
     * constructor.
     */
    public InternalServerErrorException(final Throwable cause) {
        super(ErrorCategories.INTERNAL_SERVER_ERROR, cause);
    }
}
