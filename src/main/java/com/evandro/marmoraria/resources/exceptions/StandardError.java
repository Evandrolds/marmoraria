package com.evandro.marmoraria.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
/**
 *
 * @author Evandro
 */
public class StandardError implements Serializable {

    private Instant timeStemp;
    private Integer status;
    private String message;
    private String path;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public StandardError() {
    }

    public Instant getTimeStemp() {
        return timeStemp;
    }

    public void setTimeStemp(Instant timeStemp) {
        this.timeStemp = timeStemp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
