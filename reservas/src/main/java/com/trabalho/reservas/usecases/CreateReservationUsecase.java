package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.repositories.ReservaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateReservationUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reservation execute(CreateReservationRequestDTO reservaRequestDTO){

        List<Reservation> reservaExists = reservaRepository.findReservaByIdRecursoAndDataInicio(reservaRequestDTO.getIdRecurso(), reservaRequestDTO.getData_inicio());

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
