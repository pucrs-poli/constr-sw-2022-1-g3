package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCase {

    @Autowired
    private ListSpecificUserUseCase listSpecificUserUseCase;

    @Autowired
    private KeycloakFeing keycloakFeing;

    public void execute(String authorization, String id){
        UserKeycloakDTO userKeycloakDTO = listSpecificUserUseCase.execute(authorization, id);

        userKeycloakDTO.setEnabled(Boolean.FALSE);
        keycloakFeing.updateUser(authorization, userKeycloakDTO, id);
    }
}
