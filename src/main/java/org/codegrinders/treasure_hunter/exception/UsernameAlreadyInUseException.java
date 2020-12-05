package org.codegrinders.treasure_hunter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "username is already in use,please try another one")

public class UsernameAlreadyInUseException extends RuntimeException{

    public UsernameAlreadyInUseException(String username) {
        super("username" + username + "is already in use,please try another one");

    }
}
