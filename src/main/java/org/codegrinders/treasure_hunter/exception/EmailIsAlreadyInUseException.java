package org.codegrinders.treasure_hunter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Email is already registered!")
public class EmailIsAlreadyInUseException extends RuntimeException {

    public EmailIsAlreadyInUseException(String email) {
        super("Email: " + email + " is already registered.");
    }
}
