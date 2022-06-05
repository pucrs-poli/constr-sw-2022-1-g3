package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.entities.Reservation;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import com.trabalho.reservas.exceptions.RecursoNaoEncontradoException;
import com.trabalho.reservas.repositories.ReservationRepository;
import com.trabalho.reservas.utils.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UpdateReservationUsecase {

    @Autowired
    private ReservationRepository reservaRepository;

    @Autowired
    private ValidateUtils validateUtils;

    public void execute(String id, CreateReservationRequestDTO reservaRequestDTO) {
        validateUtils.validateRequestBody(reservaRequestDTO);
        if (isNull(reservaRequestDTO.getEnabled())) {
            throw new ErroNegocioException("enabled should not be null!");
        }
        Optional<Reservation> reservationOptional = reservaRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Reservation not found");
        }

        Reservation reservation = reservationOptional.get();
        BeanUtils.copyProperties(reservaRequestDTO, reservation);

        reservaRepository.save(reservation);
    }
}
