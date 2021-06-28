package com.zhechev.kindergarten.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(KindergartenNotFoundException.class)
    public ModelAndView notMatch(KindergartenNotFoundException registerAndLoginNotMatchException) {
        ModelAndView modelAndView = new ModelAndView("users/error.html");
        modelAndView.addObject("message", registerAndLoginNotMatchException.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ModelAndView notFound(GroupNotFoundException registerAndLoginNotMatchException) {
        ModelAndView modelAndView = new ModelAndView("users/error.html");
        modelAndView.addObject("message", registerAndLoginNotMatchException.getMessage());
        return modelAndView;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        body.put("errors", fieldErrors);
        return new ResponseEntity<>(body, headers, status);
    }
}
