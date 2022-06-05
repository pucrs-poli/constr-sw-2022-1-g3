package com.trabalho.reservas.middleware;

import com.trabalho.reservas.exceptions.AuthorizationException;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthMiddleware extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.contains("swagger-ui")) {
            return true;
        }
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader);
        if (Objects.isNull(authHeader) || authHeader.isBlank()) {
            throw new AuthorizationException("Usuario nao autenticado");
        }

        //VALIDAR COM O KEYCLOAK

        return true;
    }

}
