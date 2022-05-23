package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ListSpecificUserUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    @Autowired
    private ReLoginUseCase reLoginUseCase;

    public UserKeycloakDTO execute(String authorization, String id) {
        try {
            return keycloakFeing.getSpecificUser(authorization, id);
        } catch (Exception e) {
            if (e.getMessage().toUpperCase().contains("401 UNAUTHORIZED")) {
                try {
                    LoginDTOResponse loginDTOResponse = reLoginUseCase.execute(authorization);
                    String auth = "Bearer " + loginDTOResponse.getAccess_token();
                    return keycloakFeing.getSpecificUser(auth, id);
                } catch (Exception e2) {
                    if (e2.getMessage().toUpperCase().contains("404 NOT FOUND")) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
                    }
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
                }
            } else if (e.getMessage().toUpperCase().contains("404 NOT FOUND")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            throw e;
        }
    }
}
