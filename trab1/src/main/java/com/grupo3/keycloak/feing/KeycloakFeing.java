package com.grupo3.keycloak.feing;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.keycloak.UpdatePasswordKeycloakDTO;
import com.grupo3.keycloak.dto.keycloak.UserKeycloakDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "keycloak", url = "http://localhost:8080")
public interface KeycloakFeing {

    @PostMapping(value = "/auth/admin/realms/master/users", produces = MediaType.APPLICATION_JSON_VALUE)
    Object createUser(
            @RequestBody UserKeycloakDTO createUserDTO,
            @RequestHeader String Authorization
    );

    @GetMapping(value = "/auth/admin/realms/master/users")
    List<UserKeycloakDTO> getUsers(
            @RequestHeader String Authorization
    );

    @GetMapping(value = "/auth/admin/realms/master/users/{id}")
    UserKeycloakDTO getSpecificUser(
            @RequestHeader String Authorization,
            @PathVariable String id
    );

    @PutMapping(value = "/auth/admin/realms/master/users/{id}")
    void updateUser(
            @RequestHeader String Authorization,
            @RequestBody UserKeycloakDTO updateUserDTO,
            @PathVariable String id
    );

    @PutMapping(value = "/auth/admin/realms/master/users/{id}/reset-password")
    void updatePassword(
            @RequestHeader String Authorization,
            @RequestBody UpdatePasswordKeycloakDTO updatePasswordDTO,
            @PathVariable String id);

    @PostMapping(value = "/auth/realms/master/protocol/openid-connect/token", headers = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    LoginDTOResponse login(
            @RequestBody LinkedMultiValueMap<String, String> formParams
    );

    @PostMapping(value = "/auth/realms/master/protocol/openid-connect/token", headers = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    LoginDTOResponse relogin(
            @RequestBody LinkedMultiValueMap<String, String> formParams
    );

}
