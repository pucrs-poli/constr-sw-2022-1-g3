package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
public class ListUsersUseCase {
    @Autowired
    private KeycloakFeing keycloakFeing;

    @Autowired
    private ReLoginUseCase reLoginUseCase;

    public List<UserKeycloakDTO> execute(String authorization) {
        try {
            return keycloakFeing.getUsers(authorization);
        } catch (Exception e) {
            if (e.getMessage().toUpperCase().toUpperCase().contains("401 UNAUTHORIZED")) {
                try {
                    LoginDTOResponse loginDTOResponse = reLoginUseCase.execute(authorization);
                    String auth = "Bearer " + loginDTOResponse.getAccess_token();
                    return keycloakFeing.getUsers(auth);
                } catch (Exception e2) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
                }
            }
            throw e;
        }
    }
}
