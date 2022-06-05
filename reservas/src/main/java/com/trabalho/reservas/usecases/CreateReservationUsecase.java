package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.repositories.ReservationRepository;
import com.trabalho.reservas.repositories.ReservationRepositoryCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateReservationUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    @Autowired
    private ReservationRepositoryCustom reservationRepositoryCustom;

    public Reservation execute(CreateReservationRequestDTO reservaRequestDTO){

        List<Reservation> reservaExists = reservationRepositoryCustom.findReservaByResourceIdAndStartDate(reservaRequestDTO.getResourceId(), reservaRequestDTO.getStartDate());

        if(!reservaExists.isEmpty()){
            throw new ErroNegocioException("Reserva para este recurso ja existente");
        }

        Reservation reserva = new Reservation();
        BeanUtils.copyProperties(reservaRequestDTO,reserva);
        reserva.setEnabled(Boolean.TRUE);

        reservaRepository.save(reserva);

        return reserva;
    }
}
