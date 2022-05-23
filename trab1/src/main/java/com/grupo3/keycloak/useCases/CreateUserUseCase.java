package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.dto.keycloak.UserAccessDTO;
import com.grupo3.keycloak.dto.request.CreateUserDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Locale;

@Service
public class CreateUserUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    @Autowired
    private ReLoginUseCase reLoginUseCase;

    public UserKeycloakDTO execute(CreateUserDTO createUserDTO, String authorization) {
        UserAccessDTO userAccessDTO = UserAccessDTO.builder()
                .view(Boolean.TRUE)
                .impersonate(Boolean.TRUE)
                .manage(Boolean.TRUE)
                .manageGroupMembership(Boolean.TRUE)
                .mapRoles(Boolean.TRUE)
                .build();
        UserKeycloakDTO createUserKeycloakDTO = UserKeycloakDTO.builder()
                .email(createUserDTO.getEmail())
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .username(createUserDTO.getUsername())
                .enabled(Boolean.TRUE)
                .createdTimestamp(new Date().getTime())
                .access(userAccessDTO)
                .build();

        try {
            keycloakFeing.createUser(createUserKeycloakDTO, authorization);
        } catch (Exception e) {
            if(e.getMessage().toUpperCase().contains("401 UNAUTHORIZED")){
                try {
                    LoginDTOResponse loginDTOResponse = reLoginUseCase.execute(authorization);
                    String auth = "Bearer " + loginDTOResponse.getAccess_token();
                    keycloakFeing.createUser(createUserKeycloakDTO, auth);
                    return createUserKeycloakDTO;
                } catch (Exception e2) {
                    if(e2.getMessage().toUpperCase().contains("401 UNAUTHORIZED")){
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
                    }
                    throw e2;
                }
            }
            throw e;
        }
        return createUserKeycloakDTO;
    }
}
