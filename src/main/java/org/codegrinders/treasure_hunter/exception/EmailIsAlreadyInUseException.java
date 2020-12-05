package org.codegrinders.treasure_hunter.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "email is already in use ")
public class EmailIsAlreadyInUseException extends RuntimeException {

    public EmailIsAlreadyInUseException(String email) {
        super("email "+ email + " is already in use ");
        System.out.println("email is already in use " + email);
    }
}
