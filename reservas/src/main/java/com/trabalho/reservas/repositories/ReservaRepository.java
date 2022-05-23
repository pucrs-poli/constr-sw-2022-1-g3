package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    @Query("{'idRecurso':?0}")
    List<Reserva> findReservaByIdRecursoAndDataInicio(String idRecurso, LocalDateTime localDateTime);
}
