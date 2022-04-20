package com.grupo3.keycloak.controller;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.dto.request.CreateUserDTO;
import com.grupo3.keycloak.dto.request.UpdatePasswordRequestDTO;
import com.grupo3.keycloak.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private ListUsersUseCase listUsersUseCase;

    @Autowired
    private ListSpecificUserUseCase listSpecificUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private UpdatePasswordUseCase updatePasswordUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public void createUser(
            @RequestBody CreateUserDTO createUserDTO,
            @RequestHeader String authorization
    ) {
        createUserUseCase.execute(createUserDTO, authorization);
    }

    @GetMapping
    public List<UserKeycloakDTO> getUsers(
            @RequestHeader String authorization
    ) {
        return listUsersUseCase.execute(authorization);
    }

    @GetMapping(value = "/{id}")
    public UserKeycloakDTO getSpecificUsers(
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        return listSpecificUserUseCase.execute(authorization, id);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(
            @RequestBody CreateUserDTO createUserDTO,
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        updateUserUseCase.execute(createUserDTO, authorization, id);
    }

    @PatchMapping(value = "{id}")
    public void updatePassword(
            @RequestBody UpdatePasswordRequestDTO updatePasswordRequestDTO,
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        updatePasswordUseCase.execute(authorization,updatePasswordRequestDTO,id);
    }

    @DeleteMapping(value = "{id}")
    public void updatePassword(
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        deleteUserUseCase.execute(authorization,id);
    }


}
