package com.team.api_teams.aspect;

import com.team.api_teams.exception.CustomArgumentNotValidException;
import com.team.api_teams.exception.CustomDatabaseException;
import com.team.api_teams.exception.CustomNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

@Aspect
@Component
public class ExceptionHandlingAspect {

    @AfterThrowing(pointcut = "execution(* com.team.api_teams.service..*.*(..)) || execution(* com.team.api_teams.controller..*.*(..))", throwing = "ex")
    public void handleServiceExceptions(Exception ex) throws Exception {
        if (ex instanceof DataIntegrityViolationException) {
            throw new CustomDatabaseException("Database integrity error: " + ex.getMessage());
        } else if (ex instanceof EntityNotFoundException) {
            throw new CustomNotFoundException("Entity not found: " + ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) ex;
            String errorMessage = validationException.getBindingResult().getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new CustomArgumentNotValidException("Arguments not valid: " + errorMessage);
        }
    }
}
