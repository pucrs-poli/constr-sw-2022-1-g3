package com.trabalho.reservas.usecases;

import com.trabalho.reservas.dto.request.CreateReservationRequestDTO;
import com.trabalho.reservas.exceptions.ErroNegocioException;
import org.springframework.stereotype.Service;

@Service
public class UpdateReservationUsecase {

    public void execute(String id, CreateReservationRequestDTO reservaRequestDTO) {

        throw new ErroNegocioException("METODO NAO IMPLEMENTADO");
    }
}
