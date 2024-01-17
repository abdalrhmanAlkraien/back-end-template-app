package com.marinabits.energietechs.infra.exception.error;


import com.marinabits.energietechs.infra.util.WebUtils;

/**
 * A specific error factory.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public final class ErrorFactory {

    //******************************************************************************
    // NOTE: the error code will be generated using following pattern              *
    // ENTECH - 6 first letters from error category - sequence no starting from 001  *
    //******************************************************************************

    /**
     * This factory method is responsible for creating a specific detailed error for the given
     * missing request header name.
     *
     * @param headerName missing header name
     * @param url        URL to help remediate the problem, or provide more information, or to API Reference
     * @return mapped error object for the giving missing request param name
     */
    public static Error getMissingRequestHeaderError(final String headerName, final String url) {

        switch (headerName) {
            default: {
                return new Error.ErrorBuilder()
                        .code("ENTECH-MRHE-001")
                        .message("[" + headerName + "] request header can't be null")
                        .url(url)
                        .build();
            }
        }
    }

    /**
     * This factory method is responsible for creating a specific detailed error for the given
     * request parameter name which is messing.
     *
     * @param parameterName missing request param name
     * @param url           URL to help remediate the problem, or provide more information, or to API Reference
     * @return mapped error object for the giving missing request param name
     */
    public static Error getMissingRequestParameterError(final String parameterName, final String url) {

        switch (parameterName) {
            default: {
                return new Error.ErrorBuilder()
                        .code("ENTECH-MRPA-001")
                        .message("request param [" + parameterName + "] can't be null")
                        .jsonPath("Data.Permissions")
                        .url(url)
                        .build();
            }
        }
    }

    /**
     * This factory method is responsible for creating a specific detailed error for the given
     * request parameter name which is messing.
     *
     * @param method not supported method name
     * @return mapped error object for the giving missing request param name
     */
    public static Error getMethodNotSupportError(final String method) {

        switch (method) {
            default: {
                return new Error.ErrorBuilder()
                        .code("ENTECH-MNSU-001")
                        .message("Method used [" + method + "] is not supported for requested resource path")
                        .url(WebUtils.getCurrentRequestUrl())
                        .build();
            }
        }
    }
}
