package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}
