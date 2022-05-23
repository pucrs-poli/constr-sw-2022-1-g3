package com.grupo3.keycloak.dto.keycloak;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTOResponse {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_expires_in;
    private String token_type;
}
