package com.trabalho.reservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("reservas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {

    @Id
    public String id;

    private LocalDateTime initial_date;
    private LocalDateTime end_date;
    private Boolean enabled;
    private String idAula;
    private String idRecurso;
    private String idUsuario;

}