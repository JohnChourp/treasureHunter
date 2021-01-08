package org.codegrinders.treasure_hunter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Email doesn't match!")
public class EmailDoesntMatchException extends RuntimeException{

    public EmailDoesntMatchException() {
        super("Email doesn't match");
    }
}
