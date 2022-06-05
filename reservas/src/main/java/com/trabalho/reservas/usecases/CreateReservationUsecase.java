package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.repositories.ReservationRepository;
import com.trabalho.reservas.repositories.ReservationRepositoryCustom;
import com.trabalho.reservas.utils.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class CreateReservationUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    @Autowired
    private ReservationRepositoryCustom reservationRepositoryCustom;

    @Autowired
    private ValidateUtils validateUtils;

    public Reservation execute(CreateReservationRequestDTO reservaRequestDTO) {
        validateUtils.validateRequestBody(reservaRequestDTO);
        validateUtils.validateDates(reservaRequestDTO.getStartDate(), reservaRequestDTO.getEndDate());
        List<Reservation> reservaExists = reservationRepositoryCustom.findReservaByResourceIdAndStartDate(reservaRequestDTO.getResourceId(), reservaRequestDTO.getStartDate());

        if (!reservaExists.isEmpty()) {
            throw new ErroNegocioException("This resource has been already reserved");
        }

        Reservation reserva = new Reservation();
        BeanUtils.copyProperties(reservaRequestDTO, reserva);
        reserva.setEnabled(Boolean.TRUE);
        reserva.setId(UUID.randomUUID().toString());

        reservaRepository.save(reserva);

        return reserva;
    }


}
