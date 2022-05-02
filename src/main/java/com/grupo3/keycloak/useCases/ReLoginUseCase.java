package com.grupo3.keycloak.useCases;

import com.grupo3.keycloak.dto.keycloak.LoginDTOResponse;
import com.grupo3.keycloak.feing.KeycloakFeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Collections;

@Service
public class ReLoginUseCase {

    @Autowired
    private KeycloakFeing keycloakFeing;

    public LoginDTOResponse execute(String refreshToken) {
        LinkedMultiValueMap<String, String> loginMap = mapLoginDtoToKeyclock(refreshToken);

        return keycloakFeing.relogin(loginMap);
    }

    private LinkedMultiValueMap<String, String> mapLoginDtoToKeyclock(String refreshToken) {
        LinkedMultiValueMap<String, String> stringMap = new LinkedMultiValueMap<>();
        stringMap.put("client_id", Collections.singletonList("admin-cli"));
        stringMap.put("grant_type", Collections.singletonList("refresh_token"));
        stringMap.put("refresh_token", Collections.singletonList(refreshToken.split(" ")[1]));

        return stringMap;
    }
}
