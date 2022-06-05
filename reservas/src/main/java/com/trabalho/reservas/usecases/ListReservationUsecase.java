package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.LessonDTO;
import com.trabalho.reservas.dto.ReservationDTO;
import com.trabalho.reservas.dto.ResourceDTO;
import com.trabalho.reservas.dto.UserDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.feing.LessonFeign;
import com.trabalho.reservas.feing.ResourcesFeign;
import com.trabalho.reservas.feing.UserFeign;
import com.trabalho.reservas.mapper.ReservationToReservationDTOMapper;
import com.trabalho.reservas.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListReservationUsecase {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationToReservationDTOMapper mapper;

    public ReservationDTO execute(String id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Reservation not found");
        }

        Reservation reservation = reservationOptional.get();
        return mapper.mapper(reservation);
    }
}
