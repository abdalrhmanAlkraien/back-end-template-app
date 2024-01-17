package com.marinabits.energietechs.infra.exception;


import com.marinabits.energietechs.infra.exception.error.BusinessErrorCodes;
import com.marinabits.energietechs.infra.exception.error.ErrorCategories;

/**
 * General ENTECH exception.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public class EnergiTechException extends ApiException {

    private BusinessErrorCodes businessErrorCode;
    private Object[] args;

    /**
     * constructor.
     *
     * @param businessErrorCode Business role exception details
     */
    public EnergiTechException(final ErrorCategories errorCategories, final BusinessErrorCodes businessErrorCode) {
        this(errorCategories, businessErrorCode, null);
    }

    /**
     * constructor.
     *
     * @param businessErrorCode Business role exception details
     * @param args              message placeholder arguments
     */
    public EnergiTechException(final ErrorCategories errorCategories, final BusinessErrorCodes businessErrorCode,
                               final Object... args) {
        super(errorCategories);
        this.businessErrorCode = businessErrorCode;
        this.args = args;
    }

    public BusinessErrorCodes getBusinessErrorCode() {
        return businessErrorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
