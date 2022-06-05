package com.trabalho.reservas.repositories;

import com.trabalho.reservas.dto.request.ListReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> findReservaByResourceIdAndStartDate(String idRecurso, LocalDateTime localDateTime);
    List<Reservation> findAllReservasEnabledWithFilter(ListReservationRequestDTO listarReservasRequestDTO);
}
