package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UpdatePasswordKeycloakDTO;
import com.grupo3.keycloak.dto.request.UpdatePasswordRequestDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePasswordUseCase {
    private final String TYPE = "password";

    @Autowired
    private KeycloakFeing keycloakFeing;

    public void execute(String authorization, UpdatePasswordRequestDTO updatePasswordRequestDTO, String id){
        UpdatePasswordKeycloakDTO updatePasswordKeycloakDTO = UpdatePasswordKeycloakDTO.builder()
                .temporary(Boolean.FALSE)
                .type(TYPE)
                .value(updatePasswordRequestDTO.getPassword())
                .build();

        keycloakFeing.updatePassword(authorization, updatePasswordKeycloakDTO, id);

    }
}
