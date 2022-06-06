package com.trabalho.reservas.usecases;

import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DeleteReservationsByResourceUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    public void execute(String id) {
        List<Reservation> reservations = reservaRepository.findAllByResourceId(id);
        if (isNull(reservations) || reservations.isEmpty()) {
            throw new RecursoNaoEncontradoException("There is no reservation for the resource informed");
        }

        for(Reservation reservation: reservations) {
            reservation.setEnabled(false);
            reservaRepository.save(reservation);
        }
    }

}
