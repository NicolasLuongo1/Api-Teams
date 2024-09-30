package com.team.api_teams.exception;

public class CustomArgumentNotValidException extends RuntimeException{

    public CustomArgumentNotValidException(String message) {
        super(message);
    }
}
