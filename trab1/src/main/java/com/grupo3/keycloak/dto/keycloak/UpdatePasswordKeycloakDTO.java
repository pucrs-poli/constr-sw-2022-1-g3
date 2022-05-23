package com.grupo3.keycloak.dto.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdatePasswordKeycloakDTO {

    private String type;
    private String value;
    private Boolean temporary;
}
