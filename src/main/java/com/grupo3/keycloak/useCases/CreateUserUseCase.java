package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.dto.keycloak.UserAccessDTO;
import com.grupo3.keycloak.dto.request.CreateUserDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateUserUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    public void execute(CreateUserDTO createUserDTO, String authorization) {
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

        keycloakFeing.createUser(createUserKeycloakDTO,authorization);
    }
}
