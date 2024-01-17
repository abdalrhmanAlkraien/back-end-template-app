package com.marinabits.energietechs.infra.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Energitechs error Info used to handle error info exception.
 *
 * @author Omar Ismail
 * @version 1.0
 */
public final class Error {

    private String errorCode;
    private String message;
    private String jsonPath;
    private String url;

    private Error() {
    }

    /**
     * constructor.
     *
     * @param errorCode specific error code.
     * @param message   A description of the error that occurred
     * @param jsonPath  json path where the error occurred if applicable
     * @param url       URL to help remediate the problem, or provide more information, or to API Reference
     */
    private Error(final String errorCode, final String message, final String jsonPath, final String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.jsonPath = jsonPath;
        this.url = url;
    }

    /**
     * constructor.
     *
     * @param errorCode errorCode
     * @param message   message
     * @param url       url
     */
    public Error(final String errorCode, final String message, final String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
    }

    /**
     * error builder class.
     */
    public static class ErrorBuilder {

        private String errorCode;
        private String message;
        private String jsonPath;
        private String url;

        public ErrorBuilder code(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ErrorBuilder jsonPath(final String jsonPath) {
            this.jsonPath = jsonPath;
            return this;
        }

        public ErrorBuilder url(final String url) {
            this.url = url;
            return this;
        }

        public Error build() {
            return new Error(errorCode, message, jsonPath, url);
        }
    }

    @JsonProperty("ErrorCode")
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @JsonProperty("Path")
    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(final String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @JsonProperty("Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
