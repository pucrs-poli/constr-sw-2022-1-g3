package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
