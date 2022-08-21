package com.egs.task.atmemulator.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class SecurityControllerAdvisor extends ExceptionMappingAuthenticationFailureHandler {

    @ExceptionHandler(DeactivationException.class)
    public ResponseEntity<Object> handleDeactivationException(
            DeactivationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Can`t authorize user. User is deactivated");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
