package com.trabalho.reservas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequestDTO {

    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;

    private String idAula;
    private String idRecurso;
    private String idUsuario;
}
