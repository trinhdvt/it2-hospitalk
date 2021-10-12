package com.team1.it2hospitalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpError.class)
    public ResponseEntity<?> handleCustomHttpError(HttpError ex, HttpServletRequest req) {
        ex.setPath(req.getRequestURI());
        return new ResponseEntity<>(ex, ex.getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex, HttpServletRequest req) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> map = fieldErrors.stream().collect(Collectors.groupingBy(FieldError::getField,
                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));

        HttpError httpError = new HttpError(map, HttpStatus.BAD_REQUEST);
        httpError.setPath(req.getRequestURI());

        return ResponseEntity.badRequest().body(httpError);
    }
}
