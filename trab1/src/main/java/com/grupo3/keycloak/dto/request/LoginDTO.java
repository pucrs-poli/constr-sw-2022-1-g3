package com.grupo3.keycloak.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String username;
    private String password;
    private String client_id;
    private String grant_type;
}
