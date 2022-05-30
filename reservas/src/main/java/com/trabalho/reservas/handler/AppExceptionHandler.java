package com.trabalho.reservas.handler;

import com.trabalho.reservas.dto.ErroDTO;
import com.trabalho.reservas.exceptions.AuthorizationException;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErroNegocioException.class)
    public ErroDTO handleBadRequest(HttpServletRequest httpServletRequest, Exception e){
        ErroDTO erroDTO;
        if(e instanceof ErroNegocioException) {
            ErroNegocioException erroNegocioException = (ErroNegocioException) e;

            erroDTO = new ErroDTO(erroNegocioException.getMessage(),HttpStatus.BAD_REQUEST.value());
        } else {
            erroDTO = new ErroDTO(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST.value()
            );
        }
        return erroDTO;
    }
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public ErroDTO handleUnauthorized(HttpServletRequest httpServletRequest, Exception e){
        ErroDTO erroDTO;
        if(e instanceof ErroNegocioException) {
            ErroNegocioException erroNegocioException = (ErroNegocioException) e;

            erroDTO = new ErroDTO(erroNegocioException.getMessage(),HttpStatus.UNAUTHORIZED.value());
        } else {
            erroDTO = new ErroDTO(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value()
            );
        }
        return erroDTO;
    }
}
