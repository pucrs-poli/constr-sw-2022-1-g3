package com.trabalho.reservas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListReservationRequestDTO {
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private String tipoRecurso; // como vai buscar pelo tipoRecurso se no banco tem somente o idRecurso?
    private String idUsuario;
    private String idRecurso;
}
