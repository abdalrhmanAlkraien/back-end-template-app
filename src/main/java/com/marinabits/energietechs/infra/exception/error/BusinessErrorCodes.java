package com.marinabits.energietechs.infra.exception.error;

import java.util.stream.Stream;

/**
 * Business Error Codes used to handle exception codes and messages.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public enum BusinessErrorCodes {

    //General
    UNAUTHORIZED_PERMISSION("GEN-001", "Unauthorized"),
    DESERIALIZE_ERROR("GEN-002", "Con`t deserialize from Json API file."),
    NO_RECORD_FOUND("GEN-003", "No record found."),
    WRONG_URL_PATTERN("GEN-004","The URL has wrong pattern"),
    PROCESS_NOT_AUTHORIZED("GEN-005","This process is not authorized"),
    CURRENT_USER_CAN_DO_THIS_ACTION("GEN-006","The current user can't do the action"),
    INVALID_STEP_ACTION("GEN-007", "Invalid step action for given draft action"),
    ID_NOT_VALID_NULL("GEN-008","Id is invalid, it must be null"),
    ID_NOT_VALID_NOT_NULL("GEN-009","Id is invalid, it must null"),
    FILE_IS_EMPTY("GEN-010","File is empty. Cannot save an empty file");

    private String code;
    private String errorMessage;
    private Object[] args;

    /**
     * constructor.
     *
     * @param code         error code
     * @param errorMessage code message
     */
    BusinessErrorCodes(final String code, final String errorMessage, final Object... args) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * stream business error code.
     *
     * @return business error codes
     */
    public static Stream<BusinessErrorCodes> stream() {
        return Stream.of(BusinessErrorCodes.values());
    }
}
