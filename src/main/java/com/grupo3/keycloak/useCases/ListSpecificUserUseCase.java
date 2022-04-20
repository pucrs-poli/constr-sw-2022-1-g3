package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListSpecificUserUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    public UserKeycloakDTO execute(String authorization, String id){
        return keycloakFeing.getSpecificUser(authorization,id);
    }
}
