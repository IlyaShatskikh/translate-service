package ru.langservice.translate.backend.exception;

import org.springframework.security.core.AuthenticationException;

import javax.validation.constraints.NotBlank;

public class UserAlreadyExistsAuthenticationException extends AuthenticationException {
    public UserAlreadyExistsAuthenticationException(@NotBlank(message = "Username can't be empty") String username) {
        super("User (username = "+ username +") already exists");
    }
}
