package com.trabalho.reservas.config;

import com.trabalho.reservas.middleware.AuthMiddleware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthMiddleware()); //Adiciona o interceptor de request pra fazer autenticacao no keycloak
    }

}
