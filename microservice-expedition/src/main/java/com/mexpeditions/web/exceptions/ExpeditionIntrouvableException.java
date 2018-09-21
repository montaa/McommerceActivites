package com.mexpeditions.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpeditionIntrouvableException extends Exception{
	
    public ExpeditionIntrouvableException(String message) {
        super(message);
    }

}
