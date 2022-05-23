package com.trabalho.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservaDTO {

    private String id;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private Boolean enabled;
    private UsuarioDTO usuario;
    private RecursoDTO recurso;
    private AulaDTO aula;
}
