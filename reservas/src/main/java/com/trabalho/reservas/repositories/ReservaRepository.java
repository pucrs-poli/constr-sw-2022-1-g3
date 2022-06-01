package com.trabalho.reservas.repositories;

import com.trabalho.reservas.entities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<Reservation, String>, ReservaRepositoryCustom{
}
