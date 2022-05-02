package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Service
public class DeleteUserUseCase {

    @Autowired
    private ListSpecificUserUseCase listSpecificUserUseCase;

    @Autowired
    private ReLoginUseCase reLoginUseCase;

    @Autowired
    private KeycloakFeing keycloakFeing;

    public void execute(String authorization, String id) {
        try {
            UserKeycloakDTO userKeycloakDTO = listSpecificUserUseCase.execute(authorization, id);
            userKeycloakDTO.setEnabled(Boolean.FALSE);
            keycloakFeing.updateUser(authorization, userKeycloakDTO, id);
        } catch (Exception e) {
            if(e.getMessage().toUpperCase().contains("401 UNAUTHORIZED")){
                try {
                    LoginDTOResponse loginDTOResponse = reLoginUseCase.execute(authorization);
                    String auth = "Bearer " + loginDTOResponse.getAccess_token();
                    UserKeycloakDTO userKeycloakDTO = listSpecificUserUseCase.execute(auth, id);
                    userKeycloakDTO.setEnabled(Boolean.FALSE);
                    keycloakFeing.updateUser(auth, userKeycloakDTO, id);
                    return;
                } catch (Exception e2) {
                    if(e2.getMessage().toUpperCase().contains("404 NOT FOUND")) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
                    }
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
                }
            } else if(e.getMessage().toUpperCase().contains("404 NOT FOUND")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            throw e;
        }

    }
}
