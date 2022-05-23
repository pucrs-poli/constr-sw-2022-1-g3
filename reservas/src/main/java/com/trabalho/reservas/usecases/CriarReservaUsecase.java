package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.ReservaRequestDTO;
import com.trabalho.reservas.entities.Reserva;
import com.trabalho.reservas.repositories.ReservaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CriarReservaUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    public void execute(ReservaRequestDTO reservaRequestDTO){

        List<Reserva> reservaExists = reservaRepository.findReservaByIdRecursoAndDataInicio(reservaRequestDTO.getIdRecurso(), reservaRequestDTO.getData_inicio());

        if(!reservaExists.isEmpty()){
            throw new IllegalArgumentException("Reserva para este recurso ja existente");
        }

        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaRequestDTO,reserva);
        reserva.setEnabled(Boolean.TRUE);

        reservaRepository.save(reserva);
    }
}
