package com.trabalho.reservas.usecases;

import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteReservationUsecase {

    @Autowired
    private ReservaRepository reservaRepository;

    public void execute(String id) {
        Optional<Reservation> reservaOptional = reservaRepository.findById(id);

        if(reservaOptional.isPresent()) {
            Reservation reserva = reservaOptional.get();
            reserva.setEnabled(false);

            reservaRepository.save(reserva);
        } else {
            throw new RecursoNaoEncontradoException("Reserva nao encontrada");
        }
    }
}
