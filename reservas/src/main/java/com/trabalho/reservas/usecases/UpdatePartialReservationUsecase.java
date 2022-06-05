package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class UpdatePartialReservationUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    public void execute(String id, CreateReservationRequestDTO reservaRequestDTO) {
        Optional<Reservation> reservationOptional = reservaRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Reservation not found");
        }

        Reservation reservation = reservationOptional.get();

        if(nonNull(reservaRequestDTO.getStartDate())){
            reservation.setStartDate(reservaRequestDTO.getStartDate());
        }

        if(nonNull(reservaRequestDTO.getEndDate())){
            reservation.setEnd_date(reservaRequestDTO.getEndDate());
        }

        if(nonNull(reservaRequestDTO.getResourceId())){
            reservation.setResourceId(reservaRequestDTO.getResourceId());
        }

        if(nonNull(reservaRequestDTO.getLessonId())){
            reservation.setLessonId(reservaRequestDTO.getLessonId());
        }

        if(nonNull(reservaRequestDTO.getUserId())){
            reservation.setUserId(reservaRequestDTO.getUserId());
        }

        reservaRepository.save(reservation);
    }
}
