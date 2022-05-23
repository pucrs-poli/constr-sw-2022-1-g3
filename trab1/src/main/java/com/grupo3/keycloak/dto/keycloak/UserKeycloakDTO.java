package com.grupo3.keycloak.dto.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserKeycloakDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Long createdTimestamp;
    private Boolean enabled;
    private UserAccessDTO access;

}
