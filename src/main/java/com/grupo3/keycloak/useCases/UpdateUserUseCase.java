package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.UserAccessDTO;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.dto.request.CreateUserDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    @Autowired
    private KeycloakFeing keycloakFeing;
    public void execute(CreateUserDTO createUserDTO, String authorization, String id){
        UserAccessDTO userAccessDTO = UserAccessDTO.builder()
                .view(Boolean.TRUE) //TODO buscar do usuario
                .impersonate(Boolean.TRUE)//TODO buscar do usuario
                .manage(Boolean.TRUE)//TODO buscar do usuario
                .manageGroupMembership(Boolean.TRUE)//TODO buscar do usuario
                .mapRoles(Boolean.TRUE)//TODO buscar do usuario
                .build();
        UserKeycloakDTO updateUserDTO = UserKeycloakDTO.builder()
                .id(id)
                .email(createUserDTO.getEmail())
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .username(createUserDTO.getUsername())
//                .enabled(Boolean.TRUE)//TODO buscar do usuario
//                .createdTimestamp(new Date().getTime())//TODO buscar do usuario
//                .access(userAccessDTO)//TODO buscar do usuario
                .build();

        keycloakFeing.updateUser(authorization, updateUserDTO, id);
    }
}
