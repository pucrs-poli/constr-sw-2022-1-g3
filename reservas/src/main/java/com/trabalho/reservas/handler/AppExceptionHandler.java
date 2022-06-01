package com.trabalho.reservas.handler;

import com.trabalho.reservas.dto.ErrorDTO;
import com.trabalho.reservas.exceptions.AuthorizationException;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
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
    public ErrorDTO handleBadRequest(HttpServletRequest httpServletRequest, Exception e) {
        ErrorDTO erroDTO;
        if (e instanceof ErroNegocioException) {
            ErroNegocioException erroNegocioException = (ErroNegocioException) e;

            erroDTO = new ErrorDTO(erroNegocioException.getMessage());
        } else {
            erroDTO = new ErrorDTO(
                    e.getMessage()
            );
        }
        return erroDTO;
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public ErrorDTO handleUnauthorized(HttpServletRequest httpServletRequest, Exception e) {
        ErrorDTO erroDTO;
        if (e instanceof ErroNegocioException) {
            ErroNegocioException erroNegocioException = (ErroNegocioException) e;

            erroDTO = new ErrorDTO(erroNegocioException.getMessage());
        } else {
            erroDTO = new ErrorDTO(
                    e.getMessage()
            );
        }
        return erroDTO;
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ErrorDTO handleNotFound(HttpServletRequest httpServletRequest, Exception e) {
        ErrorDTO erroDTO;
        if (e instanceof RecursoNaoEncontradoException) {
            RecursoNaoEncontradoException erroNegocioException = (RecursoNaoEncontradoException) e;

            erroDTO = new ErrorDTO(erroNegocioException.getMessage());
        } else {
            erroDTO = new ErrorDTO(
                    e.getMessage()
            );
        }
        return erroDTO;
    }
}
