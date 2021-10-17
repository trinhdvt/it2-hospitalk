package com.team1.it2hospitalk.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed"})
public class HttpError extends RuntimeException {

    private final Object message;

    @lombok.Getter
    private final HttpStatus status;

    @lombok.Getter
    @lombok.Setter
    private String path;

    /**
     * @param message Message will be response to client. This field's type should be {@link String} or {@link Map}
     * @param status  {@link HttpStatus} code
     */
    public HttpError(Object message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return this.message.toString();
    }

}
