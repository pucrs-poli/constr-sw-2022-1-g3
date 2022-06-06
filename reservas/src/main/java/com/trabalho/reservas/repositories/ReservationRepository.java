package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findAllByResourceId(String resourcesId);
}
