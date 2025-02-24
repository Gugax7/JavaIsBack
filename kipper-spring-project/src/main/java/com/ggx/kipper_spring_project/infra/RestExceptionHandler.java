package com.ggx.kipper_spring_project.infra;

import com.ggx.kipper_spring_project.exceptions.PathNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
    //@ExceptionHandler(NoResourceFoundException.class)
    private ResponseEntity<String> noResource(NoResourceFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Path not found!");
    }
    @ExceptionHandler(PathNotFoundException.class)
    private ResponseEntity<String> notFoundHandler(PathNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Path not found");
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Broh, this url doesnt exist!");
    }
}
