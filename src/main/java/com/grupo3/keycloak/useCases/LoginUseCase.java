package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.dto.request.LoginDTO;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Collections;
import java.util.Map;

@Service
public class LoginUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    public LoginDTOResponse execute(LoginDTO loginDTO) {
        LinkedMultiValueMap<String, String> loginMap = mapLoginDtoToKeyclock(loginDTO);

        return keycloakFeing.login(loginMap);
    }

    private LinkedMultiValueMap<String, String> mapLoginDtoToKeyclock(LoginDTO loginDTO){
        LinkedMultiValueMap<String, String> stringMap = new LinkedMultiValueMap<>();
        stringMap.put("client_id", Collections.singletonList(loginDTO.getClient_id()));
        stringMap.put("username", Collections.singletonList(loginDTO.getUsername()));
        stringMap.put("password", Collections.singletonList(loginDTO.getPassword()));
        stringMap.put("grant_type", Collections.singletonList(loginDTO.getGrant_type()));

        return stringMap;
    }
}
