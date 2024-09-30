package com.team.api_teams.exception;

public class CustomDatabaseException extends RuntimeException {
    public CustomDatabaseException(String message) {
        super(message);
    }
}