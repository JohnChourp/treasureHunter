package org.codegrinders.treasure_hunter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Password doesn't match!")
public class PasswordDoesntMatchException extends RuntimeException{

    public PasswordDoesntMatchException() {
        super("You have given wrong password.");
    }
}
