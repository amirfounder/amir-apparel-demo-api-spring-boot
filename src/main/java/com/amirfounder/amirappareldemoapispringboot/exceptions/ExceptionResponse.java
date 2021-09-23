package com.amirfounder.amirappareldemoapispringboot.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String error;
    private String errorMessage;

    public ExceptionResponse() {}

    public ExceptionResponse(Date timestamp, String error, String errorMessage) {
        this.timestamp = timestamp;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
