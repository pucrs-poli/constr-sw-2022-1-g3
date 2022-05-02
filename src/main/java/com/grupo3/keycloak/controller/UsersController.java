package com.grupo3.keycloak.controller;

import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import com.grupo3.keycloak.dto.request.CreateUserDTO;
import com.grupo3.keycloak.dto.request.UpdatePasswordRequestDTO;
import com.grupo3.keycloak.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public UserKeycloakDTO createUser(
            @RequestBody CreateUserDTO createUserDTO,
            @RequestHeader String authorization
    ) {
        validateAuthorization(authorization);
        return createUserUseCase.execute(createUserDTO, authorization);
    }

    @GetMapping
    public List<UserKeycloakDTO> getUsers(
            @RequestHeader String authorization
    ) {
        validateAuthorization(authorization);
        return listUsersUseCase.execute(authorization);
    }

    @GetMapping(value = "/{id}")
    public UserKeycloakDTO getSpecificUsers(
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        validateAuthorization(authorization);
        return listSpecificUserUseCase.execute(authorization, id);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(
            @RequestBody CreateUserDTO createUserDTO,
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        validateAuthorization(authorization);
        updateUserUseCase.execute(createUserDTO, authorization, id);
    }

    @PatchMapping(value = "{id}")
    public void updatePassword(
            @RequestBody UpdatePasswordRequestDTO updatePasswordRequestDTO,
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        validateAuthorization(authorization);
        updatePasswordUseCase.execute(authorization, updatePasswordRequestDTO, id);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @RequestHeader String authorization,
            @PathVariable String id
    ) {
        validateAuthorization(authorization);
        deleteUserUseCase.execute(authorization, id);
    }

    private void validateAuthorization(String auth){
        if(auth.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
    }

}
