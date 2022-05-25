package com.trabalho.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ErroNegocioException extends RuntimeException{

    public ErroNegocioException(String message) {
        super(message);
    }
}
