package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersUseCase {
    @Autowired
    private KeycloakFeing keycloakFeing;

    public List<UserKeycloakDTO> execute(String authorization) {
        return keycloakFeing.getUsers(authorization);
    }
}
