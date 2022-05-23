package com.grupo3.keycloak.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
