package com.trabalho.reservas.usecases;

import com.trabalho.reservas.entities.Reserva;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletarReservaUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    public void execute(String id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if(reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            reserva.setEnabled(false);

            reservaRepository.save(reserva);
        } else {
            throw new ErroNegocioException("Reserva nao encontrada");
        }
    }
}
