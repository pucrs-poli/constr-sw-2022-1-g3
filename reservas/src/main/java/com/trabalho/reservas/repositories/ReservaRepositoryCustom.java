package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepositoryCustom {
    List<Reserva> findReservaByIdRecursoAndDataInicio(String idRecurso, LocalDateTime localDateTime);
}
