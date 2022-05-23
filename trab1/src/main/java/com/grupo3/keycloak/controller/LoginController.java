package com.grupo3.keycloak.controller;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.request.LoginDTO;
import com.grupo3.keycloak.useCases.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginUseCase loginUseCase;


    @PostMapping
    public LoginDTOResponse login(
            @RequestBody LoginDTO loginDTO
    ) {
        if (loginDTO.getClient_id().isBlank()
                || loginDTO.getGrant_type().isBlank()
                || loginDTO.getUsername().isBlank()
                || loginDTO.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return loginUseCase.execute(loginDTO);
    }
}
