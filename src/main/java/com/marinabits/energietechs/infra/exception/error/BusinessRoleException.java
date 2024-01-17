package com.marinabits.energietechs.infra.exception.error;


import com.marinabits.energietechs.infra.exception.EnergiTechException;

/**
 * Business role exception used to handle role exception.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public class BusinessRoleException extends EnergiTechException {

    /**
     * Constructor.
     *
     * @param businessErrorCode businessErrorCode
     */
    public BusinessRoleException(final BusinessErrorCodes businessErrorCode) {
        super(ErrorCategories.BUSINESS_ROLE_ERROR, businessErrorCode);
    }

    /**
     * Constructor.
     *
     * @param businessErrorCode businessErrorCode
     * @param args              args
     */
    public BusinessRoleException(final BusinessErrorCodes businessErrorCode, final Object... args) {
        super(ErrorCategories.BUSINESS_ROLE_ERROR, businessErrorCode, args);
    }
}
